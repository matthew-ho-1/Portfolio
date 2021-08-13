############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
.text:

create_term:
	addi $sp, $sp, -12 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	beqz $s0, returnNegative #branch if coeff is 0
	bltz $s1, returnNegative #branch if exp is negative
	li, $a0, 12
	li $v0, 9
	syscall #call syscall sbrk allocating 12 bytes
	move $s2, $v0 #move to register
	sw $s0, 0($s2) #store coeff
	sw $s1, 4($s2) #store exp
	li $t0, 0 
	sw $t0, 8($s2) #store address of 0
	add $v0, $0, $s2 #return address 
	j doneWithCreateTerm
	
	returnNegative: #return -1 
	addi $v0, $0, -1
	j doneWithCreateTerm
	
	doneWithCreateTerm:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	addi $sp, $sp, 12 #restore s registers
	jr $ra
init_polynomial:
	addi $sp, $sp, -12 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	lw $t0, 0($s1) #store pair[0] (coeff)
	lw $t1, 4($s1) #store pairp[1] (exp)
	addi $sp, $sp, -12 #create term 
	add $a0, $0, $t0
	add $a1, $0, $t1
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal create_term
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 12
	bltz $t0, returnNegativeInit #branch if result is negative
	sw $t0, 0($s0) #store address into p
	addi $v0, $0, 1 #return 1
	j doneWithInitPoly
	
	returnNegativeInit: #return -1
	addi $v0, $0, -1
	j doneWithInitPoly
	
	doneWithInitPoly:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	addi $sp, $sp, 12 #restore s registers
	jr $ra
add_N_terms_to_polynomial:
	addi $sp, $sp, -20 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	sw $s3, 12($sp)
	sw $s4, 16($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	beqz $s2, returnZeroTerms #branch if N is 0
	bltz $s2, returnZeroTerms #branch if N is less than 0
	li $t1, 0 #counter
	add $t2, $0, $s2 #load N into new register
	
	loopThroughTermsArray: #loop through terms array
	beqz $t2, returnNumTerm #if N is 0, branch
	lw $t3, 0($s1) #store coeff into register
	lw $t4, 4($s1) #store exp into register
	lw $s3, 0($s0) #load the head of poly
	beqz $t3, checkExp
	addi $sp, $sp, -16 #create term 
	add $a0, $0, $t3
	add $a1, $0, $t4
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal create_term
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	bltz, $t0,  skipTerm #branch if result is negative
	li $t8, 0 #counter for poly
	lw $t7, 0($s0)
	beqz $t7, addNewHead #if head is empty branch
	loopThroughPoly: #looping through the polyn
	lw $t5, 4($s3) #grab the exp of term
	lw $t6, 8($s3) #grab the pointer to next term
	beqz $t6, handleLastTerm #branch if next pointer is 0
	beq $t4, $t5, skipTerm #branch if current exponent is equal to last term's exponent
	bgt $t4, $t5, addHead #if current exp term is greater than exponent in poly branch
	blt $t6, $t4, addTerm #if exponent in poly is less than current exp term, branch
	add $s4, $0, $s3 #load old pointer into register
	add $s3, $0, $t6 #set next pointer 
	addi $t8, $t8, 1 #increase counter for poly
	j loopThroughPoly
	
	continueLooping: #continue through Terms array
	addi $t1, $t1, 1 #add 1 to counter
	skipTerm: #skips increasing counter
	addi $t2, $t2, -1 #decrement N
	addi $s1, $s1, 8 #increase pointer to terms array
	j loopThroughTermsArray
	
	handleLastTerm: #handle last term of poly
	bgt $t4, $t5, checkHowMany #branch if current exponent is greater than last term's exponent
	beq $t4, $t5, skipTerm #branch if current exponent is equal to last term's exponent
	sw $t0, 8($s3) #store new term's address into current term's next
	j continueLooping
	
	addNewHead: #add new head
	sw $t0, 0($s0) #store new address into head
	j continueLooping
	
	checkHowMany: #checks to see how many terms are in poly
	beqz $t8, addHead #branch if there is only one term in poly
	j addTerm
	
	addTerm: #adding a term
	lw $t7, 8($s4) #store prev term's next into register
	sw $t7, 8($t0) #store prev term's next into new term's next
	sw $t0, 8($s4) #store new term's address into prev term's next
	j continueLooping #jump back to loop
	
	addHead: #add new head
	lw $t6, 0($s0) #store address of old head into register
	sw $t6, 8($t0) #store address into the pointer's next of new term
	sw $t0, 0($s0) #store new term's address into p
	j continueLooping #jump back to loop	
	
	checkExp: #check the exponent to see if it is negative
	bltz $t4, returnNumTerm #branch if exponent is less than 0
	j skipTerm
	
	returnNumTerm: #returns the number of terms added
	add $v0, $0, $t1
	j doneWithAddNTerms
	
	returnZeroTerms: #return 0
	addi $v0, $0, 0
	j doneWithAddNTerms
	
	doneWithAddNTerms:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	lw $s4, 16($sp)
	addi $sp, $sp, 20 #restore s registers
	jr $ra
update_N_terms_in_polynomial:
	addi $sp, $sp, -16 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	sw $s3, 12($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	beqz $s2, returnNZero #branch if N is 0
	bltz $s2, returnNZero #branch if N is less than 0
	lw $t0, 0($s0)
	beqz $t0, returnNZero #branch if p is null
	li $t1, 0 #counter
	add $t2, $0, $s2 #load N into new register
	li $a0, 4
	li $v0, 9
	syscall
	add $t0, $0, $v0
	li $t7, 0
	sw $t7, 0($t0) #store 0 into heap for updated array
	
	loopThroughTermsArrayUpdate: #loop through terms array
	beqz $t2, returnNumUpdated #if N is 0, branch
	lw $t3, 0($s1) #store coeff into register
	lw $t4, 4($s1) #store exp into register
	lw $s3, 0($s0) #load the head of poly
	beqz $t3, checkExpUpdate #if coeff is 0 branch
	bltz $t4, skipTermUpdate
	li $t8, 0 #counter for poly
	loopThroughPolyUpdate: #looping through the polyn
	lw $t5, 4($s3) #grab the exp of term
	lw $t6, 8($s3) #grab the pointer to next term
	beqz $t6, handleLastTermUpdate #branch if next pointer is 0
	beq $t4, $t5, loopThroughHeap
	add $s3, $0, $t6 #set next pointer 
	addi $t8, $t8, 1 #increase counter for poly
	j loopThroughPolyUpdate
	
	continueLoopingUpdate: #continue through Terms array
	addi $t1, $t1, 1 #add 1 to counter
	skipTermUpdate: #skips increasing counter
	addi $t2, $t2, -1 #decrement N
	addi $s1, $s1, 8 #increase pointer to terms array
	j loopThroughTermsArrayUpdate
		
	loopThroughHeap: #loop through heap
	lw $t7, 0($t0) #load exp
	beqz $t7, storeCoeff #if coeff is 0, branch
	beq $t4, $t7, handleDuplicate #if coeff matches the one in terms array branch
	addi $t0, $t0, -4 #decrement heap pointer
	j loopThroughHeap
	
	handleDuplicate: #handle duplicate exp
	sw $t3, 0($s3) #store new coeff into register
	j skipTermUpdate 
	
	storeCoeff: #store new coeff into term
	sw $t3, 0($s3) #store new coeff into register
	li $a0, 4 #create new space on the heap
	li $v0, 9
	syscall
	add $t0, $0, $v0
	sw $t4, 0($t0) #store updated exp on heap
	j continueLoopingUpdate

	handleLastTermUpdate: #handle last term of poly
	bne $t4, $t5, skipTermUpdate #if the last poly exp is not equal to current exp in terms array, branch
	beqz $t8, storeCoeff #branch if only one term in polu
	j loopThroughHeap
	
	checkExpUpdate: #check the exponent to see if it is negative
	bltz $t4, returnNumUpdated #branch if exponent is less than 0
	j skipTermUpdate #jump back to loop
	
	returnNumUpdated: #returns the number of terms added
	add $v0, $0, $t1
	j doneWithUpdateNTerms
	
	returnNZero: #return 0
	addi $v0, $0, 0
	j doneWithUpdateNTerms
	
	doneWithUpdateNTerms:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	addi $sp, $sp, 16  #restore s registers
	jr $ra
get_Nth_term:
	addi $sp, $sp, -20 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	sw $s3, 12($sp)
	sw $s4, 16($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	lw $t0, 0($s0)
	beqz $t0, returnNotFound #branch if p is null
	li $t0, 1 #counter
	add $s2, $0, $s0 #store head into register
	lw $s3, 0($s2) #load address in head 
	getPoly: #loop through the polyn
	lw $t1, 0($s3) #load coeff
	lw $t2, 4($s3) #load exp
	lw $s4, 8($s3) #load pointer
	beqz $s4, checkLastTerm #branch if pointer is null
	beq $t0, $s1, returnFound #branch if found index
	add $s3, $0, $s4 #move to next term
	addi $t0, $t0, 1 #increase counter
	j getPoly
	
	checkLastTerm: #check last term
	lw $t1, 0($s3) #load coeff
	lw $t2, 4($s3) #load exp
	beq $t0, $s1, returnFound #branch if index equal to counter
	returnNotFound:
	addi $v0, $0, -1 #return not found
	addi $v1, $0, 0
	j doneWithGetTerm
	
	returnFound: #return coeff and exp
	add $v0, $0, $t2
	add $v1, $0, $t1
	j doneWithGetTerm
	
	doneWithGetTerm:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	lw $s4, 16($sp)
	addi $sp, $sp, 20  #restore s registers
	jr $ra
remove_Nth_term:
	addi $sp, $sp, -24 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	sw $s3, 12($sp)
	sw $s4, 16($sp)
	sw $s5, 20($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	lw $t0, 0($s0)
	beqz $t0, returnNotFound #branch if p is null
	li $t0, 1 #counter
	add $s2, $0, $s0 #store head into register
	lw $s3, 0($s2) #load address in head 
	removePoly: #loop through the polyn
	lw $t1, 0($s3) #load coeff
	lw $t2, 4($s3) #load exp
	lw $s4, 8($s3) #load pointer
	beqz $s4, checkLastTermRemove #branch if pointer is null
	beq $t0, $s1, remove #branch if found index
	add $s5, $0, $s3 #save old pointer into register
	add $s3, $0, $s4 #move to next term
	addi $t0, $t0, 1 #increase counter
	j removePoly
	
	checkLastTermRemove: #check last term
	lw $t1, 0($s3) #load coeff
	lw $t2, 4($s3) #load exp
	beq $t0, $s1, removeTail #branch if found index
	addi $v0, $0, -1 #return not found
	addi $v1, $0, 0
	j doneWithGetTerm
	
	removeTail: #removing tail
	sw $0, 8($s5) #store 0 into previous pointer
	j returnRemove
	
	remove: #remove node
	li $t3, 1
	beq $s1, $t3, removeHead #branch if at head
	sw $s4, 8($s5) #store prev term's pointer to current pointer
	returnRemove: #return coeff and exp removed
	add $v0, $0, $t2
	add $v1, $0, $t1
	j doneWithGetTerm
	
	removeHead: #removing head
	sw $s4, 0($s0) #setting next pointer to new head
	j returnRemove
	
	doneWithRemove:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	lw $s4, 16($sp)
	lw $s5, 20($sp)
	addi $sp, $sp, 24  #restore s registers
	jr $ra
add_poly:
	addi $sp, $sp, -28 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	sw $s3, 12($sp)
	sw $s4, 16($sp)
	sw $s5, 20($sp)
	sw $s6, 24($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	add $s3, $0, $s0 #store head of p into register
	add $s4, $0, $s1 #store head of q into register
	li $t6, 0 #counter
	lw $s5, 0($s3) #load address in head of p
	lw $s6, 0($s4) #load address in head of q
	beqz $s5, checkQNull #if p is null branch
	beqz $s6, checkPNull #if q is null branch
	addPoly: #loop through the polyn
	lw $t0, 0($s5) #load coeff of p
	lw $t1, 4($s5) #load exp of p
	lw $t2, 8($s5) #load pointer of p
	lw $t3, 0($s6) #load coeff of q
	lw $t4, 4($s6) #load exp of q
	lw $t5, 8($s6) #load pointer of q
	beqz $t2, compareLastTerm #branch if p pointer is null
	beqz $t5, compareLastTerm #branch if q pointer is null
	bne $t1, $t4, compareExp #branch if exponents are not equal
	add $t7, $t0, $t3 #add coeff
	beqz $t7, skipToNext #branch if coeff add up to 0
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t7, 0($t8) #store combined coeff into term
	sw $t1, 4($t8) #store exp into term
	addi $t6, $t6, 1 #increase counter
	skipToNext: #advancing counter
	add $s5, $0, $t2 #move to next term (p)
	add $s6, $0, $t5 #move to next term (q)
	j addPoly
	
	compareExp: #compare exponents if not equal
	bgt $t1, $t4, handleP #branch if exp at p is greater than q
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t3, 0($t8) #store q coeff into term
	sw $t4, 4($t8) #store q exp into term
	add $s6, $0, $t5 #move to next term (q)
	addi $t6, $t6, 1 #increase counter
	j addPoly
	
	handleP: #handle exp at p
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t0, 0($t8) #store p coeff into term
	sw $t1, 4($t8) #store p exp into term
	add $s5, $0, $t2 #move to next term (p)
	addi $t6, $t6, 1 #increase counter
	j addPoly
	
	compareLastTerm: #compare last term
	bnez $t2, addP #if P is not null, branch
	bnez $t5, addQ #if Q is not null, branch
	j oneTerm #jump to one term
	
	addQ: #loop through Q terms
	beq $t1, $t4, addLikeTerm #branch if exponents from p and q are the same
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t3, 0($t8) #store p coeff into term
	sw $t4, 4($t8) #store p exp into term
	add $s6, $0, $t5 #move to next term (p)
	addi $t6, $t6, 1 #increase counter
	loopThruQ: #loop through Q term
	lw $t3, 0($s6) #load coeff of q
	lw $t4, 4($s6) #load exp of q
	lw $t5, 8($s6) #load pointer of q
	beqz $t5, oneTerm #branch if pointer is 0
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t3, 0($t8) #store p coeff into term
	sw $t4, 4($t8) #store p exp into term
	add $s6, $0, $t5 #move to next term (p)
	addi $t6, $t6, 1 #increase counter
	j loopThruQ
	
	addP: #loop Through P terms
	beq $t1, $t4, addLikeTerm #branch if exponents from p and q are the same
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t0, 0($t8) #store p coeff into term
	sw $t1, 4($t8) #store p exp into term
	add $s5, $0, $t2 #move to next term (p)
	addi $t6, $t6, 1 #increase counter
	loopThruP:
	lw $t0, 0($s5) #load coeff of p
	lw $t1, 4($s5) #load exp of p
	lw $t2, 8($s5) #load pointer of p
	beqz $t2, oneTerm #branch if pointer is 0
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t0, 0($t8) #store p coeff into term
	sw $t1, 4($t8) #store p exp into term
	add $s5, $0, $t2 #move to next term (p)
	addi $t6, $t6, 1 #increase counter
	j loopThruP
	
	addLikeTerm: #adds like term
	add $t7, $t0, $t3 #add coeff
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t7, 0($t8) #store combined coeff into term
	sw $t1, 4($t8) #store exp into term
	addi $t6, $t6, 1 #increae counter
	bnez $t2, increaseP #if P is not null, branch
	bnez $t5, increaseQ #if Q is not null, branch
	
	increaseP: #increase P's counter
	add $s5, $0, $t2 #move to next term (p)
	j loopThruP #loop back to P
	
	increaseQ: #increase Q's counter
	add $s6, $0, $t5 #move to next term (p)
	j loopThruQ #loop back to Q
	
	oneTerm: #handling one term
	bne $t1, $t4, addLast #if exponents are not equal, branch
	add $t7, $t0, $t3 #add coeff
	beqz $t7, checkCounter #if coeff add up to 0, branch
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t7, 0($t8) #store combined coeff into term
	sw $t1, 4($t8) #store exp into term
	addi $t6, $t6, 1 #increase counter
	j addToNewPoly
	
	checkCounter: #check counter
	beqz $t6, failAdding #if only 1 term in poly branch
	j addToNewPoly #skip to add new poly
	
	addLast: #handle adding different exp for one term in polys
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t0, 0($t8) #store p coeff into term
	sw $t1, 4($t8) #store p exp into term
	addi $t6, $t6, 1 #increase counter
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t3, 0($t8) #store q coeff into term
	sw $t4, 4($t8) #store q exp into term
	addi $t6, $t6, 1 #increase counter
	j addToNewPoly #jump to add new poly
	
	addToNewPoly: #adds all the terms in heap to poly
	add $t7, $0, $t6 #store counter 
	addi $t7, $t7, -1  #decrement counter
	li $t0, 0 #end of terms array
	li $t1, -1
	li $a0, 8
	li $v0, 9
	syscall
	add $s6, $0, $v0 #store address into register
	sw $t0, 0($s6) #store 0 into term
	sw $t1, 4($s6) #store -1 into term
	li $t0, 8
	mult $t6, $t0
	mflo $t6
	sub $s5, $s6, $t6  #store address of the beginning of the array
	addi $sp, $sp, -16 #initialize poly
	add $a0, $0, $s2
	add $a1, $0, $s5
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal init_polynomial
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	bltz $t0, failAdding #branch if result is less than 0
	beqz $t7, returnSuccessful #branch if only one term
	addi $sp, $sp, -16 #store rest of terms in array
	add $a0, $0, $s2
	add $a1, $0, $s5
	add $a2, $0, $t6
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal add_N_terms_to_polynomial
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	beqz $t0, failAdding #branch if result is equal to 0
	returnSuccessful:
	addi $v0, $0, 1 #return when sucessfully adding
	j doneWithAddPoly
	
	checkQNull: #checks to see if q is null
	beqz $s6, failAdding #branch if q is null
	loopThruQEmpty: #loop through q
	lw $t3, 0($s6) #load coeff of q
	lw $t4, 4($s6) #load exp of q
	lw $t5, 8($s6) #load pointer of q
	beqz $t5, handleQEmpty
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t3, 0($t8) #store q coeff into term
	sw $t4, 4($t8) #store q exp into term
	add $s6, $0, $t5 #move to next term (q)
	addi $t6, $t6, 1 #increase counter
	j loopThruQEmpty
	
	handleQEmpty: #handle last term of q
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t3, 0($t8) #store q coeff into term
	sw $t4, 4($t8) #store q exp into term
	addi $t6, $t6, 1 #increase counter
	j addToNewPoly #jump to add new poly
	
	checkPNull: #checking P 
	loopThruPEmpty: #looping through P
	lw $t0, 0($s5) #load coeff of p
	lw $t1, 4($s5) #load exp of p
	lw $t2, 8($s5) #load pointer of p
	beqz $t2, handlePEmpty
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t0, 0($t8) #store p coeff into term
	sw $t1, 4($t8) #store p exp into term
	add $s5, $0, $t2 #move to next term (p)
	addi $t6, $t6, 1 #increase counter
	j loopThruPEmpty
	
	handlePEmpty: #handling last term of P
	li $a0, 8
	li $v0, 9
	syscall
	add $t8, $0, $v0 #store address into register
	sw $t0, 0($t8) #store p coeff into term
	sw $t1, 4($t8) #store p exp into term
	addi $t6, $t6, 1 #increase counter
	j addToNewPoly #jump to add new poly
	
	failAdding: #return when failing to add
	addi $v0, $0, 0
	j doneWithAddPoly
	
	doneWithAddPoly:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	lw $s4, 16($sp)
	lw $s5, 20($sp)
	lw $s6, 24($sp)
	addi $sp, $sp, 28 #restore s registers
	jr $ra
mult_poly:
	addi $sp, $sp, -28 #preserve s registers
	sw $s0, 0($sp)
	sw $s1, 4($sp)
	sw $s2, 8($sp)
	sw $s3, 12($sp)
	sw $s4, 16($sp)
	sw $s5, 20($sp)
	sw $s6, 24($sp)
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	add $s3, $0, $s0 #store head of p into register
	add $s4, $0, $s1 #store head of q into register
	li $t6, 0 #counter
	lw $s5, 0($s3) #load address in head of p
	lw $s6, 0($s4) #load address in head of q
	beqz $s5, checkQNullMult #if p is null branch
	beqz $s6, addPolyn #if q is null branch
	loopThroughPPoly:
	lw $t0, 0($s5) #load coeff of p
	lw $t1, 4($s5) #load exp of p
	lw $t2, 8($s5) #load pointer of p
	beqz $t2, loopThroughQPoly #branch if p pointer is null
	loopThroughQPoly:
	lw $t3, 0($s6) #load coeff of q
	lw $t4, 4($s6) #load exp of q
	lw $t5, 8($s6) #load pointer of q
	beqz $t5, checkLastTermQ #branch if q pointer is null
	beqz $t6, handleFirstTime #branch if handling first time
	mult $t0, $t3 #multiply coeff
	mflo $t7
	add $t8, $t1, $t4 #add exponent
	lw $s1, 4($s3) #load exponent of previous term
	beq $s1, $t8, addCoeffTerm #branch if previous coeff matches current exponent
	li $a0, 8
	li $v0, 9
	syscall
	add $s3, $0, $v0 #store address into register
	sw $t7, 0($s3) #store coeff into term
	sw $t8, 4($s3) #store exp into term
	addi $t6, $t6, 1 #increase counter
	continueThruQ: #continue to loop through Q
	add $s6, $0, $t5 #move to next term (q)
	j loopThroughQPoly
	continueThruP: #continue to loop through P
	add $s5, $0, $t2 #move to next term (p)
	lw $s6, 0($s4) #load address in head of q
	j loopThroughPPoly
	
	addCoeffTerm: #add coeff together
	lw $t8, 0($s3) #grab coeff from term
	add $t8, $t8, $t7 #add with current coeff
	sw $t8, 0($s3) #store new coeff into term
	j continueThruQ #jump back to Q loop
	
	handleFirstTime: #handle first terrm
	mult $t0, $t3 #multiply coeff
	mflo $t7
	add $t8, $t1, $t4 #add exponent
	li $a0, 8
	li $v0, 9
	syscall
	add $s3, $0, $v0 #store address into register
	sw $t7, 0($s3) #store coeff into term
	sw $t8, 4($s3) #store exp into term
	addi $t6, $t6, 1 #increase counter
	j continueThruQ #jump back to loop
	
	checkLastTermQ: #check last term of Q
	mult $t0, $t3 #multiply coeff
	mflo $t7
	add $t8, $t1, $t4 #add exponent
	lw $s1, 4($s3) #load exponent of previous term
	beq $s1, $t8, addCoeffTerm #branch if prev exp matches current exp
	li $a0, 8
	li $v0, 9
	syscall
	add $s3, $0, $v0 #store address into register
	sw $t7, 0($s3) #store coeff into term
	sw $t8, 4($s3) #store exp into term
	addi $t6, $t6, 1 #increase counter
	beqz $t2, addToNewPolyMult #branch if reach the end of both p and q
	j continueThruP #jump back to p loop

	checkQNullMult: #checks to see if q is null
	beqz $s6, failMultiply #branch if q is null
	j addPolyn #jump to add poly
	
	addPolyn: #adds poly when p or q is null
	addi $sp, $sp, -16 #initialize poly
	add $a0, $0, $s0
	add $a1, $0, $s1
	add $a2, $0, $s2
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal add_poly
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	beqz $t0, failMultiply #branch if result is less than 0
	addi $sp, $sp, 16
	j returnSuccessfulMult #jump to return
	
	failMultiply: #return if failed to multiply
	addi $v0, $0, 0
	j doneWithMultPoly
	
	addToNewPolyMult: #adds all the terms in heap to poly
	add $t7, $0, $t6 #store counter 
	addi $t7, $t7, -1  #decrement counter
	li $t0, 0 #end of terms array
	li $t1, -1
	li $a0, 8
	li $v0, 9
	syscall
	add $s3, $0, $v0 #store address into register
	sw $t0, 0($s3) #store 0 into term
	sw $t1, 4($s3) #store -1 into term
	li $t0, 8
	mult $t6, $t0
	mflo $t6
	sub $s5, $s3, $t6  #store address of the beginning of the array
	addi $sp, $sp, -16 #initialize poly
	add $a0, $0, $s2
	add $a1, $0, $s5
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal init_polynomial
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	bltz $t0, failMultiply #branch if result is less than 0
	beqz $t7, returnSuccessfulMult #branch if only one term
	addi $sp, $sp, -16 #store rest of terms in array
	add $a0, $0, $s2
	add $a1, $0, $s5
	add $a2, $0, $t6
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal add_N_terms_to_polynomial
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	beqz $t0, failAdding #branch if result is equal to 0
	returnSuccessfulMult:
	addi $v0, $0, 1 #return when sucessfully adding
	j doneWithMultPoly
	
	doneWithMultPoly:
	lw $s0, 0($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)
	lw $s3, 12($sp)
	lw $s4, 16($sp)
	lw $s5, 20($sp)
	lw $s6, 24($sp)
	addi $sp, $sp, 28 #restore s registers
	jr $ra
