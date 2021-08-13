############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
.text:

str_len:  #counts the number of characters in a string
	add $s0, $0, $a0
	li $t0, 0  #total num of characters
	countCharacters: #loops through string
	lb $t1 0($s0)
	beqz $t1, returnLength #branch if reach null terminated character
	addi $t0, $t0, 1 #increment total
	addi $s0, $s0, 1 #increment pointer
	j countCharacters
	
	returnLength: #return length	
	add $v0, $0, $t0
	jr $ra
str_equals: #compares two strings to see if they are equal
	add $s0, $0, $a0
	add $s1, $0, $a1
	addi $sp, $sp, -12 #get length of s0's string
	add $a0, $0, $s0 
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal str_len
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store length into t0
	addi $sp, $sp, 12
	addi $sp, $sp, -16 #get length of s1's string
	add $a0, $0, $s1
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $t0, 12($sp)
	jal str_len
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $t0, 12($sp)
	add $t1, $0, $v0 #store length into t1
	addi $sp, $sp, 16
	bne $t0, $t1, returnNotEqual #branch if lengths are not equal
	compareCharacter: #loop through each character and compare
	lb $t0, 0($s0) #store s0's character in t0
	lb $t1, 0($s1) #stire s1's character in t1
	bne $t0, $t1, returnNotEqual #branch if t0 and t1 are not equal
	beqz $t0, returnEqual #branch if reach null terminated character
	beqz $t1, returnEqual #branch if reach null terminated character
	addi $s0, $s0, 1  #increment s0's pointer
	addi $s1, $s1, 1  #increment s1's pointer
	j compareCharacter
	
	returnNotEqual: #return if not equal
	addi $v0, $0, 0
	j doneWithEquals
	
	returnEqual: #return if equal
	addi $v0, $0, 1
	j doneWithEquals
	
	doneWithEquals:
	jr $ra
str_cpy: #copies one string from one register to another
	add $s0, $0, $a0
	add $s1, $0, $a1
	li $t0, 0 #total num of characters
	copyCharacters: #loop through string
	lb $t1, 0($s0) #load character into register
	beqz $t1, returnCopylength #branch if reach null terminate character
	sb $t1, 0($s1) #store character into new register
	addi $s0, $s0, 1 #increment pointer of old register
	addi $s1, $s1, 1 #increment pointer of new register
	addi $t0, $t0, 1 #increment total
	j copyCharacters
	
	returnCopylength:  #return the length of the copy
	li $t1, 0
	sb $t1, 0($s1)
	add $v0, $0, $t0
	jr $ra
create_person: #create a person node
	add $s0, $0, $a0
	lb $t0, 0($s0) #loads total nodes into register
	lb $t1, 16($s0) #loads current num of nodes into register
	addi $t1, $t1, 1 
	bgt $t1, $t0, noMoreSpace #if can't add anymore nodes, branch
	lb $t2 8($s0)#loads size of node into register
	addi $t1, $t1, -1 
	mult $t1, $t2 #multiply size of node by current node
	mflo $t4
	addi $t3, $t4, 36 #add 36 
	addi $t1, $t1, 1 #increase current node count
	sb $t1, 16($s0)
	lb $t0, 16($s0)
	add $v0, $s0, $t3 #return the pointer
	j doneWithCreatePerson
	
	noMoreSpace: #return if no more space
	addi $v0, $0, -1
	j doneWithCreatePerson
	
	doneWithCreatePerson:
	jr $ra
is_person_exists: #checks to see if a person exists in the Network
	add $s0, $0, $a0
	add $s1, $0, $a1
	lb $t1, 0($s0) #get total num of nodes
	lb $t2, 8($s0) #get size of node
	mult $t1, $t2 #total num * size of node
	mflo $t3 #load into register
	addi $t3, $t3, 36 #add 36 
	blt $s1, $t3, returnNotAPerson #branch if less than person array
	add $t4, $s0, $t3 #store the first byte of the edge array
	beq $s1, $t4, returnNotAPerson #branch if equal to first byte of edge array
	bgt $s1, $t4, returnNotAPerson #branch if greater to first byte of edge array
	lb $t0, 0($s1) #load first character in the node
	beqz $t0, returnNotAPerson #if the character is 0, branch
	addi $v0, $0, 1 #return is person
	j doneWithIsPersonExist
	
	returnNotAPerson: #return is not a person
	addi $v0, $0, 0
	j doneWithIsPersonExist
	
	doneWithIsPersonExist:
	jr $ra
is_person_name_exists: #checks to see if a person's name exists in the Network
	add $s0, $0, $a0
	add $s1, $0, $a1
	lb $t1, 16($s0) #get total num of nodes 
	lb $t2, 8($s0) #get size of node 
	addi $t0, $s0, 36 #jump to where set of nodes starts

	networkCheckName: #loop through each node
	beqz $t1, returnDNE #branch if no more nodes to check
	addi $sp, $sp, -24 #check to see if name arg = name at node
	add $a0, $0, $s1
	add $a1, $0, $t0
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $t0, 12($sp)
	sw $t1, 16($sp)
	sw $t2, 20($sp)
	jal str_equals
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $t0, 12($sp)
	lw $t1, 16($sp)
	lw $t2, 20($sp)
	add $t3, $0, $v0 #store result
	addi $sp, $sp, 24
	bnez $t3, returnFoundName #branch if name matches node
	addi $t1, $t1, -1 #decrement counter
	add $t0, $t0, $t2 #increase pointer by size of node
	j networkCheckName
	
	returnFoundName: #return if found name
	addi $v0, $0, 1
	add $v1, $0, $t0
	j doneWithIsPersonNameExist
	
	returnDNE: #return if did not find name
	addi $v0, $0, 0
	j doneWithIsPersonNameExist

	doneWithIsPersonNameExist:
	jr $ra
add_person_property:  #adds a person to the node
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	add $s3, $0, $a3
	addi $sp, $sp, -12 #count length of the name argument
	add $a0, $0, $s3
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal str_len
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t2, $0, $v0 #store result
	addi $sp, $sp, 12
	lb $t1, 8($s0) #store size of node 
	bgt $t2, $t1, sizeTooBig #branch if name is bigger than the size of node
	addi $t0, $s0, 24
	addi $sp, $sp, -12 #check to see if bytes 24-28 match with arg "Name"
	add $a0, $0, $s2
	add $a1, $0, $t0
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal str_equals
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t1, $0, $v0 #store result
	addi $sp, $sp, 12
	beqz $t1, propertyNotName #branch if bytes 24-28 don't match with arg "Name"
	lb $t1, 16($s0)
	beqz $t1, noNodesInNetwork
	addi $sp, $sp, -12 #check to see if name already exists in network
	add $a0, $0, $s0
	add $a1, $0, $s3
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal is_person_name_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t1, $0, $v0 #store result
	addi $sp, $sp, 12
	bnez $t1, notUniqueName #branch if name is already in network
	lb $t0 16($s0)
	addi $t0, $t0, 1
	sb $t0, 16($s0)
	loopToAddNode: #loop through string and add to node
	lb $t1, 0($s3) #load char from string
	beqz $t1, returnAddSuccess #branch if null terminated
	sb $t1, 0($s1) #store byte into node
	addi $s1, $s1, 1 #increase node pointer
	addi $s3, $s3, 1 #increase string pointer
	j loopToAddNode
	
	returnAddSuccess: #return when adding successfully
	lb $t1, -5($s1) #store byte into node
	li $v0, 11
	move $a0, $t1
	syscall
	addi $v0, $0, 1
	j doneWithAddPersonProperty
	
	propertyNotName: #return if property is not "Name"
	addi $v0, $0, 0
	j doneWithAddPersonProperty
	
	noNodesInNetwork: #return if there are no nodes in the network
	addi $v0, $0, -1
	j doneWithAddPersonProperty
	
	sizeTooBig: #return if the size of name is bigger than size of nodes
	addi $v0, $0, -2
	j doneWithAddPersonProperty
	
	notUniqueName: #return if the name is not unique
	addi $v0, $0, -3
	j doneWithAddPersonProperty
	
	doneWithAddPersonProperty:
	jr $ra
get_person:  #checks to see if a person's name exists in the Network
	add $s0, $0, $a0
	add $s1, $0, $a1
	lb $t1, 16($s0) #get total num of nodes 
	lb $t2, 8($s0) #get size of node 
	addi $t0, $s0, 36 #jump to where set of nodes starts

	getPersonName: #loop through each node
	beqz $t1, returnDNEGetPerson #branch if no more nodes to check
	addi $sp, $sp, -24 #check to see if name arg = name at node
	add $a0, $0, $s1
	add $a1, $0, $t0
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $t0, 12($sp)
	sw $t1, 16($sp)
	sw $t2, 20($sp)
	jal str_equals
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $t0, 12($sp)
	lw $t1, 16($sp)
	lw $t2, 20($sp)
	add $t3, $0, $v0 #store result
	addi $sp, $sp, 24
	bnez $t3, returnGetPersonFound #branch if name matches node
	addi $t1, $t1, -1 #decrement counter
	add $t0, $t0, $t2 #increase pointer by size of node
	j getPersonName
	
	returnGetPersonFound: #return if found name
	add $v0, $0, $t0
	j doneWithGetPerson
	
	returnDNEGetPerson: #return if did not find name
	addi $v0, $0, 0
	j doneWithGetPerson

	doneWithGetPerson:
	jr $ra
	
is_relation_exists:  #checks to see if people are related
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2 
	lb $t1, 0($s0) #get total num of nodes
	lb $t2, 8($s0) #get size of nodes
	addi $t0, $s0, 36 #jump to where nodes start
	mult $t1, $t2 #size of node * total node
	mflo $t3 #store into register
	lb $t2, 12($s0) #get size of edge nodes
	add $t0, $t0, $t3 #jump to where set of edges starts
	lb $t4, 20($s0) #get current number of edges
	networkCheckRelation:
	beqz $t4, returnDNERelation
	lw $t5, 0($t0) #load first address into reg
	lw $t6, 4($t0) #load second address into reg
	beq $t5, $s1, checkT6 #branch if t5 = s1
	beq $t6, $s1, checkT5 #branch if t6 = s1
	continueChecking: 
	add $t0, $t0, $t2 #increase pointer by size
	addi $t4, $t4, -1 #decrease counter
	j networkCheckRelation
	
	checkT6: #check to see if t6 = s2
	beq $t6, $s2, returnFoundRelation #branch if t6 = s2
	j continueChecking #go back to loop
	
	checkT5: #check to see if t5 = s2
	beq $t5, $s2, returnFoundRelation #branch if t5 = s2
	j continueChecking #go back to loop

	returnFoundRelation: #return if found relation
	addi $v0, $0, 1
	j doneWithIsRelationExist
	
	returnDNERelation: #return if did notfind relation
	addi $v0, $0, 0
	j doneWithIsRelationExist

	doneWithIsRelationExist:
	jr $ra
add_relation: #add relation to network
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	addi $sp, $sp, -12 #check to see if person s1 exists
	add $a0, $0, $s0
	add $a1, $0, $s1
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal is_person_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 12
	beqz $t0, personDNE #branch if person doesn't exist
	addi $sp, $sp, -12 #check to see if person s2 exists
	add $a0, $0, $s0
	add $a1, $0, $s2
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal is_person_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 12
	beqz $t0, personDNE #branch if person doesn't exist
	lb $t0, 20($s0) #store current num of edges
	lb $t1, 0($s0) #store total num of edges
	beq $t0, $t1, noMoreEdgeRoom #if current num = total num edges, branch
	bgt $t0, $t1, noMoreEdgeRoom #if current num edge > total num edges, branch
	addi $sp, $sp, -16 #check to see if relation exists
	add $a0, $0, $s0
	add $a1, $0, $s1
	add $a2, $0, $s2
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal is_relation_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	bnez $t0, edgeAlreadyExists #branch if edge already exists
	beq $s1, $s2, samePeople #branch if arguments are the same people
	lb $t1, 0($s0) #get total num of nodes
	lb $t2, 8($s0) #get size of nodes
	addi $t0, $s0, 36 #jump to where nodes start
	mult $t1, $t2 #size of node * total node
	mflo $t3 #store into register
	add $t0, $t0, $t3 #jump to where set of edges starts
	lb $t4, 20($s0) #get current number of edges
	lb $t1, 12($s0) #get size of edge
	mult $t1, $t4 #current number of edges * size of edge
	mflo $t3 #store into register
	add $t0, $t0, $t3 #move pointer to current node
	sw $s1, 0($t0) #stores $s1 into first node
	sw $s2, 4($t0) #stores $s2 into second node
	lb $t1 20($s0) #gets current number of edges
	addi $t1, $t1, 1 #adds one
	sb $t1 20($s0) #stores it back into current number of edges
	addi $v0, $0, 1 #return successful add
	j doneWithAddRelation
	
	personDNE: #return if person doesn't exist
	addi $v0, $0, 0
	j doneWithAddRelation
	
	noMoreEdgeRoom: #return if there is no more room in edge nodes
	addi $v0, $0, -1
	j doneWithAddRelation
	
	edgeAlreadyExists: #return if edge already exists
	addi $v0, $0, -2
	j doneWithAddRelation
	
	samePeople: #return if arguments are the same people
	addi $v0, $0, -3
	j doneWithAddRelation
	
	doneWithAddRelation:
	jr $ra
add_relation_property: #add relation property to network
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	add $s3, $0, $a3
	lw $s4, 0($sp)
	addi $sp, $sp, 4
	addi $sp, $sp, -16 #check to see if relation exists
	add $a0, $0, $s0
	add $a1, $0, $s1
	add $a2, $0, $s2
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	jal is_relation_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 16
	beqz $t0, relationDNE #branch if relation does not exist
	addi $t0, $s0, 29
	addi $sp, $sp, -12 #check to see if name s3 arg = name at node
	add $a0, $0, $s3
	add $a1, $0, $t0
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal str_equals
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 12
	beqz $t0, notFriendProp #branch if friend arg not equal to friend property in network
	bltz $s4, friendPropLessThan0 #branch if friend property less than 0
	lb $t1, 0($s0) #get total num of nodes
	lb $t2, 8($s0) #get size of nodes
	addi $t0, $s0, 36 #jump to where nodes start
	mult $t1, $t2 #size of node * total node
	mflo $t3 #store into register
	lb $t2, 12($s0) #get size of nodes
	add $t0, $t0, $t3 #jump to where set of edges starts
	lb $t4, 20($s0) #get current number of edges
	findRelation:
	lw $t5, 0($t0) #load first address into reg
	lw $t6, 4($t0) #load second address into reg
	beq $t5, $s1, checkT6FR #branch if t5 = s1
	beq $t6, $s1, checkT5FR #branch if t6 = s1
	continueFinding: 
	add $t0, $t0, $t2 #increase pointer by size
	j findRelation
	
	checkT6FR: #check to see if t6 = s2
	beq $t6, $s2, foundRelation #branch if t6 = s2
	j continueFinding #go back to loop
	
	checkT5FR: #check to see if t5 = s2
	beq $t5, $s2, foundRelation #branch if t5 = s2
	j continueFinding #go back to loop
	
	foundRelation: #insert friend prop in position
	sw $s4, 8($t0)
	addi $v0, $0, 1 #return add friend prop successfully
	j doneWithAddRelationProp
	
	relationDNE: #return if relation doesn't exist
	addi $v0, $0, 0
	j doneWithAddRelationProp
	
	notFriendProp: #return if friend arg not equal to friend property in network
	addi $v0, $0, -1
	j doneWithAddRelationProp
	
	friendPropLessThan0: #return if friend property less than 0
	addi $v0, $0, -2
	j doneWithAddRelationProp
	
	doneWithAddRelationProp:
	jr $ra
is_friend_of_friend:
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	addi $sp, $sp, -12 #check to see if person s1 exists
	add $a0, $0, $s0
	add $a1, $0, $s1
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal is_person_name_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store result
	add $s3, $0, $v1 #store address of person
	addi $sp, $sp, 12
	beqz $t0, nameDNE #branch if name doesn't exist
	addi $sp, $sp, -12 #check to see if person s2 exists
	add $a0, $0, $s0
	add $a1, $0, $s2
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	jal is_person_name_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	add $t0, $0, $v0 #store result
	add $s4, $0, $v1 #store address of person
	addi $sp, $sp, 12
	beqz $t0, nameDNE #branch if name doesn't exist
	addi $sp, $sp, -8 #check to see if the two people are related
	add $a0, $0, $s0
	add $a1, $0, $s3
	add $a2, $0, $s4
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	jal is_relation_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	add $t0, $0, $v0 #store result
	addi $sp, $sp, 8
	bnez $t0, notFOF
	lb $t1, 0($s0) #get total num of nodes
	lb $t2, 8($s0) #get size of nodes
	addi $t0, $s0, 36 #jump to where nodes start
	mult $t1, $t2 #size of node * total node
	mflo $t3 #store into register
	lb $t2, 12($s0) #get size of edge nodes
	add $t0, $t0, $t3 #jump to where set of edges starts
	lb $t4, 20($s0) #get current number of edges
	findIsFOF:
	beqz $t4, notFOF
	lw $t5, 0($t0) #load first address into reg
	lw $t6, 4($t0) #load second address into reg
	beq $t5, $s3, checkT6FOF #branch if t5 = s1
	beq $t6, $s3, checkT5FOF #branch if t6 = s1
	continueCheckingFOF: 
	add $t0, $t0, $t2 #increase pointer by size
	addi $t4, $t4, -1 #decrease counter
	j findIsFOF
	
	checkT6FOF: 
	addi $sp, $sp, -8 #check to see if the two people are related
	add $a0, $0, $s0
	add $a1, $0, $t6
	add $a2, $0, $s4
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $t4, 8($sp)
	sw $t0, 12($sp)
	jal is_relation_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $t4, 8($sp)
	lw $t0, 12($sp)
	add $t1, $0, $v0 #store result
	addi $sp, $sp, 8
	bnez $t1, isFOF
	j continueCheckingFOF #go back to loop
	
	checkT5FOF: #check to see if t5 = s2
	addi $sp, $sp, -8 #check to see if the two people are related
	add $a0, $0, $s0
	add $a1, $0, $t5
	add $a2, $0, $s4
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $t4, 8($sp)
	sw $t0, 12($sp)
	jal is_relation_exists
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $t4, 8($sp)
	lw $t0, 12($sp)
	add $t1, $0, $v0 #store result
	addi $sp, $sp, 8
	bnez $t1, isFOF
	j continueCheckingFOF #go back to loop
	
	nameDNE: #return when name does not exist
	addi $v0, $0, -1
	j doneWithFOF 
	
	notFOF: #return when person is not friend of friend
	addi $v0, $0, 0
	j doneWithFOF
	
	isFOF: #return when person is friend of friend
	addi $v0, $0, 1
	j doneWithFOF
	
	doneWithFOF:
	jr $ra