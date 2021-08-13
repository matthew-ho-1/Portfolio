#Name: Matthew Ho
#NetID: mwho
#ID: 112509194

############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################

.text

load_game: #loading the game state
	add $s0, $0, $a0
	add $s1, $0, $a1
	li $v0, 13 #opening file
	move $a0, $s1
	li $a1, 0
	li $a2, 0
	syscall
	add $s2, $0, $v0 #grabs file descriptor
	li $s3, -1 
	beq $s3, $s2, notRealFile #if file is not a real file, branch out
	li $s1, 0
	li $t8, 3
	addi $s7, $sp, 24
	sb $s1, 4($s0) #give default value of 0 moves
	li $t7, 'B'
	sb $t7, 5($s0) #give default value of player 'B'

	loopThruFirstThree: #loops through first three rows of file
	li $s4, 10
	li $s5, 13
	beqz $t8, checkFirstThree
	li $v0, 14 #reading file
	move $a0, $s2
	move $a1, $s7
	li $a2, 1
	syscall	
	lb $s6, 0($s7) #reads the character from the file
	beq $s6, $s5, windows #if character equal to /r, branch out
	beq $s6, $s4, other #if character equal to /n, branch out
	addi $s6, $s6, -48 #parses the ascii number 
	mult $s4, $s1
	mflo $t2
	add $s1, $t2, $s6 #adds to total
	j loopThruFirstThree
	
	windows: #handle if windows
	li $t0, 3
	li $t1, 2
	li $t2, 1
	beq $t8, $t0, storeTop #branch if first row
	beq $t8, $t1, saveBot #branch if second row
	sb $s1 2($s0) #save last row
	sb $s1 3($s0)
	j storeBot
	continueWin:
	li $v0, 14 #skip over the /r/n in windows
	move $a0, $s2
	move $a1, $s7
	li $a2, 1
	syscall
	add $s1,$0, $0 #reset to 0
	addi $t8, $t8, -1  #decrease counter
	j loopThruFirstThree
	
	saveBot: #saves the bot value into a register
	add $t3, $0, $s1
	beqz $t2, continueOther #branch if came from not Windows
	j continueWin #jump back to handle windows
	
	other: #handle if other
	li $t0, 3
	li $t1, 2
	li $t2, 0
	beq $t8, $t0, storeTop #branch if first row
	beq $t8, $t1, saveBot #branch if second row
	sb $s1 2($s0) #save last row
	sb $s1 3($s0)
	j storeBot
	continueOther:
	add $s1,$0, $0 #reset to 0
	addi $t8, $t8, -1 #decrement coutner
	j loopThruFirstThree
	
	storeTop: #store in Top mancala
	addi $sp, $sp, -24  #store value into top mancala
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s1
  	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s2, 8($sp)
	sw $t8, 12($sp)
	sw $s7, 16($sp)
	sw $t2, 20($sp)
  	jal put_stones
  	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s2, 8($sp)
	lw $t8, 12($sp)
	lw $s7, 16($sp)
	lw $t2, 20($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 24
	beqz $t2, continueOther
	j continueWin
	
	storeBot: #store in Bottom mancala
	addi $sp, $sp, -24  #store value into top mancala
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $t3
  	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s2, 8($sp)
	sw $t8, 12($sp)
	sw $s7, 16($sp)
	sw $t2, 20($sp)
  	jal put_stones
  	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s2, 8($sp)
	lw $t8, 12($sp)
	lw $s7, 16($sp)
	lw $t2, 20($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 24
  	beqz $t2, continueOther
	j continueWin
	
	checkFirstThree: #checks to see if the first three rows are valid	
	li $t3, 99
	lb $t0, 0($s0)
	lb $t1, 1($s0)
	add $t4, $t0, $t1
	bgt $t4, $t3, checkPocket
	li $t0, 'T'
	sb $t0, 5($s0)
	addi $t1, $0, 0 #total sum of pockets
	addi $s1, $0, 0
	checkLastTwo: #check last two rows
	li $s4, 10
	li $s5, 13
	li $v0, 14 #reading file
	move $a0, $s2
	move $a1, $s7
	li $a2, 2
	syscall
	add $t8, $0, $v0
	beqz $t8, finalCheck
	lb $s6, 0($s7) #reads the character from the file
	lb $s3, 1($s7)
	beq $s6, $s5, handleWindows #if character equal to /r, loop back
	beq $s6, $s4, handleOther  #If file is Unix, branch out out
	addi $s6, $s6, -48 #parse number
	addi $s3, $s3, -48
	mult $s4, $s6
	mflo $t6
	add $t4, $t6, $s3
	lb $t0 5($s0)
	addi $sp, $sp, -24  #store new number into pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
	add $a3, $0, $t4
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	sw $s1, 8($sp)
  	sw $s2, 12($sp)
  	sw $s7, 16($sp)
  	sw $t1, 20($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	lw $s1, 8($sp)
  	lw $s2, 12($sp)
  	lw $s7, 16($sp)
  	lw $t1, 20($sp)
  	add $s4, $0, $v0 
  	add $t1, $t1, $s4
  	addi $sp, $sp, 24
  	bltz $s4, checkPocket
  	li $t2, 'B'
  	li $t3, 'T'
  	lb $t4 5($s0)
  	beq $t4, $t2, goDown #branch if first row
  	addi $s1, $s1, 1 #increment pointer
	j checkLastTwo
	
	goDown:
	addi $s1, $s1, -1 #decrement pointer
	j checkLastTwo #jump back to loop
	
	handleWindows: #handle if windows
	li $t0, 'B' #switch to bottom row
	sb $t0, 5($s0)
	lb $t0, 2($s0)
 	addi $t0, $t0, -1 #decrement counter
	add $s1, $0, $t0 #reset pointer
	j checkLastTwo
	
	handleOther: #if file is Unix, parse accordingly
	li $t0, 'B' #switch to bottom row
	sb $t0, 5($s0)
	lb $t0, 2($s0)
	addi $t0, $t0, -1 #decrement counter
	add $s1, $0, $t0 #reset pointer
	li $v0, 14 #reading file
	move $a0, $s2
	move $a1, $s7
	li $a2, 1
	syscall
	lb $s6 0($s7)
	addi $s3, $s3, -48
	addi $s6, $s6, -48 #parse number
	mult $s4, $s3
	mflo $t6
	add $t4, $t6, $s6
	lb $t0 5($s0)
	addi $sp, $sp, -24  #store new number into pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
	add $a3, $0, $t4
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	sw $s1, 8($sp)
  	sw $s2, 12($sp)
  	sw $s7, 16($sp)
  	sw $t1, 20($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	lw $s1, 8($sp)
  	lw $s2, 12($sp)
  	lw $s7, 16($sp)
  	lw $t1, 20($sp)
  	add $s4, $0, $v0 
  	add $t1, $t1, $s4
  	addi $sp, $sp, 24
  	bltz $s4, checkPocket
  	addi $s1, $s1, -1
	j checkLastTwo
	
	checkPocket: #checking pocket to see if valid
	addi $t3, $t3, -1
	lb $t0, 2($s0)
	lb $t1, 3($s0)
	add $t4, $t0, $t1
	bgt $t4, $t3, invalidReturn #branch if invalid
	li $v0, 16  #close file
	move $a0, $s2 
	syscall
	addi $v0, $0, 0 #return not valid stones
	add $v1, $0, $t4 #return number of pockets
	j doneWithFile
		
	finalCheck: #last check
	li $t0, 99
	bgt $t1, $t0, checkPocket #branch if invalid number of stones
	addi $t0, $t0, -1
	lb $t0, 2($s0)
	lb $t1, 3($s0)
	add $t4, $t0, $t1
	bgt $t0, $t4, invalidReturn #branch if both pockets and number of stones are invalid
	li $v0, 16  #close file
	move $a0, $s2 
	syscall
	addi $v0, $0, 1 #return valid stones
	add $v1, $0, $t4  #return number of pockets
	j doneWithFile
	
	invalidReturn: #return if not valid
	li $v0, 16 #close file
	move $a0, $s2 
	syscall
	addi $v0, $0, 0 
	addi $v1, $0, 0
	j doneWithFile
	
	notRealFile: #if file does not exist, return -1 
	addi $v0, $0, -1
	addi $v1, $0, -1
	j doneWithFile
	
	doneWithFile:
	jr $ra
	
put_stones: #add stones to the player's mancala
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	li $t0, 'B'
	li $t1, 'T'
	li $s3, 0
	li $t7, 10
	addi $s6, $s0, 0 # bot mancala
	addi $s7, $s0, 1 # top mancala
	beq $s1, $t0, putStonesB #if player is B branch
	beq $s1, $t1, putStonesT #if player is T branch
	j doneWithPutStones
	
	putStonesB: #adding to Player B's mancala
	lb $t2, 2($s0) #getting to the pocket specified by Player B
	lb $t3, 3($s0)
	add $t8, $t2, $t3
	sll $t8, $t8, 1
	addi $t8, $t8, 8
	add $s0, $s0, $t8
	lb $t0 0($s0)
	sb $s2, 0($s6)
	div $s2, $t7
	mflo $t4
	mfhi $t5
	addi $t4, $t4, 48 #parsing back to ascii
	addi $t5, $t5, 48
	sb $t4 0($s0)	  #storing new value into player's mancala
	sb $t5 1($s0)
	add $v0, $0, $s2
	j doneWithPutStones
	
	putStonesT: #adding to Player T's mancala
	addi $s0, $s0, 6 
	sb $s2, 0($s7)
	div $s2, $t7
	mflo $t2
	mfhi $t3
	addi $t2, $t2, 48 #parasing back to ascii
	addi $t3, $t3, 48
	sb $t2 0($s0)	  #storing new value into player's mancala
	sb $t3 1($s0)
	add $v0, $0, $s2
	j doneWithPutStones
	
	doneWithPutStones:
	jr $ra

get_pocket: #getting the value at a pocket
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	li $t0, 'B'
	li $t1, 'T'
	li $t3, 10
	li $t4, 0
	beq $s1, $t0, checkDistanceB #if player is B branch
	beq $s1, $t1, checkDistanceT #if player is T branch
	j error
	
	checkDistanceB: #Checking B's row
	lb $t2, 2($s0)
	addi $t2, $t2, -1
	bgt $s2, $t2, error #if distance greater than pockets in row, branch
	bltz $s2, error
	lb $t2, 2($s0) #getting to the pocket specified by distance
	lb $t7, 3($s0)
	add $t8, $t2, $t7
	sll $t8, $t8, 1
	addi $t8, $t8, 8
	add $s0, $s0, $t8
	addi $s0, $s0, -2
	sll $s2, $s2, 1
	sub $s0, $s0, $s2
	lb $t5, 0($s0) #grabbing characters from the pocket
	lb $t6, 1($s0)
	addi $t5, $t5, -48 #parsing the ascii values into decimal
	addi $t6, $t6, -48
	mult $t3, $t5
	mflo $t4
	add $t4, $t4, $t6
	add $v0, $0, $t4
	j doneWithGetPocket
	
	checkDistanceT: #Checking T's row
	lb $t2, 2($s0)
	addi $t2, $t2, -1
	bgt $s2, $t2, error #if distance greater than pockets in row, branch
	bltz $s2, error
	addi $s2, $s2, 1 #getting to the pocket specified by distance
	sll $s2, $s2, 1
	addi $s2, $s2, 6
	add $s0, $s0, $s2
	lb $t5, 0($s0)
	lb $t6, 1($s0)
	addi $t5, $t5, -48
	addi $t6, $t6, -48
	mult $t3, $t5
	mflo $t4
	add $t4, $t4, $t6
	add $v0, $0, $t4
	j doneWithGetPocket
	
	error: #returning error
	addi $v0, $0, -1
	
	doneWithGetPocket:
	jr $ra
set_pocket: #setting value at pocket
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	add $s3, $0, $a3
	li $t0, 'B'
	li $t1, 'T'
	li $t8, 99
	li $t7, 10
	beq $s1, $t0, checkSetPocketB #if player is B branch
	beq $s1, $t1, checkSetPocketT #if player is T branch
	j errorSetPocket
	
	checkSetPocketB: #Checking B's row
	lb $t2, 2($s0)
	addi $t2, $t2, -1
	bgt $s2, $t2, errorSetPocket #if distance greater than pockets in row, branch
	bgt $s3, $t8, errorSize #if size greater than 99, branch
	bltz $s3, errorSize
	div $s3, $t7
	mflo $s4 #first digit
	mfhi $s5 #second digit
	addi, $s4, $s4, 48 #parsing into ascii value
	addi, $s5, $s5, 48
	lb $t2, 2($s0) #getting to the pocket specified by distance
	lb $t7, 3($s0)
	add $t8, $t2, $t7
	sll $t8, $t8, 1
	addi $t8, $t8, 8
	add $s0, $s0, $t8
	addi $s0, $s0, -2
	sll $s2, $s2, 1
	sub $s0, $s0, $s2
	sb $s4, 0($s0)
	sb $s5, 1($s0)
	add $v0, $0, $s3
	j doneWithSetPocket
	
	checkSetPocketT: #Checking T's row
	lb $t2, 2($s0)
	addi $t2, $t2, -1
	bgt $s2, $t2, errorSetPocket #if distance greater than pockets in row, branch
	bgt $s3, $t8, errorSize #if size greater than 99, branch
	bltz $s3, errorSize
	div $s3, $t7
	mflo $s4 #first digit
	mfhi $s5 #second digit
	addi, $s4, $s4, 48 #parsing into ascii value
	addi, $s5, $s5, 48
	addi $s2, $s2, 1
	sll $s2, $s2, 1
	addi $s2, $s2, 6
	add $s0, $s0, $s2
	sb $s4, 0($s0)
	sb $s5, 1($s0)
	add $v0, $0, $s3
	j doneWithSetPocket
	
	errorSize: #returning error with size
	addi $v0, $0, -2
	j doneWithSetPocket
	 
	errorSetPocket: #returning error with distance greater than pockets in row, branch
	addi $v0, $0, -1
	
	doneWithSetPocket:
	jr $ra
collect_stones: #add stones to the player's mancala
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	li $t0, 'B'
	li $t1, 'T'
	li $s3, 0
	li $t7, 10
	addi $s6, $s0, 0 # bot mancala
	addi $s7, $s0, 1 # top mancala
	beq $s1, $t0, collectStonesB #if player is B branch
	beq $s1, $t1, collectStonesT #if player is T branch
	j notValidPlayer
	
	collectStonesB: #adding to Player B's mancala
	beqz $s2, notValidStones
	bltz $s2, notValidStones
	lb $t2, 2($s0) #getting to the pocket specified by Player B
	lb $t3, 3($s0)
	add $t8, $t2, $t3
	sll $t8, $t8, 1
	addi $t8, $t8, 8
	add $s0, $s0, $t8
	lb $t0, 0($s0)	#parsing the number
	lb $t1, 1($s0)
	addi $t0, $t0, -48
	addi $t1, $t1, -48
	mult $t0, $t7
	mflo $s3
	add $s3, $s3, $t1
	add $s3, $s3, $s2  #adding the stones to the player's mancala
	sb $s3, 0($s6)
	div $s3, $t7
	mflo $t4
	mfhi $t5
	addi $t4, $t4, 48 #parsing back to ascii
	addi $t5, $t5, 48
	sb $t4 0($s0)	  #storing new value into player's mancala
	sb $t5 1($s0)
	add $v0, $0, $s2
	j doneWithCollectStones
	
	collectStonesT: #adding to Player T's mancala
	beqz $s2, notValidStones
	bltz $s2, notValidStones
	addi $s0, $s0, 6 
	lb $t0 0($s0) 	#parsing the number
	lb $t1 1($s0)
	addi $t0, $t0, -48 
	addi $t1, $t1, -48
	mult $t0, $t7
	mflo $s3
	add $s3, $s3, $t1 #adding the stones to the player's mancala
	add $s3, $s3, $s2
	sb $s3, 0($s7)
	div $s3, $t7
	mflo $t2
	mfhi $t3
	addi $t2, $t2, 48 #parasing back to ascii
	addi $t3, $t3, 48
	sb $t2 0($s0)	  #storing new value into player's mancala
	sb $t3 1($s0)
	add $v0, $0, $s2
	j doneWithCollectStones
	
	notValidPlayer: #error with not valid player
	addi $v0, $0, -1
	j doneWithCollectStones
	
	notValidStones: #error with stones grater than 0
	addi $v0, $0, -2
	j doneWithCollectStones
	
	doneWithCollectStones:
	jr $ra
verify_move: #verifying move by player
	add $s0, $0, $a0
	add $s1, $0, $a1
	add $s2, $0, $a2
	lb $t0 5($s0)
	lb $t2 2($s0)
	addi $t2, $t2, -1
	li $t1, 99
	beq $t1, $s2, switchTurns #if distance = 99, branch
	bgt $s1, $t2, invalidRowSize #if origin pocket is greater than row size branch
	addi $sp, $sp, -8  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	add $s3, $0, $v0 
  	addi $sp, $sp, 8
  	beqz $s3, emptyPocket #if pocket is empty branch
  	beqz $s2, distanceInvalid #if distance = 0, branch
  	bne $s3, $s2, distanceInvalid #if distance not equal to num stones in pocket, branch
  	addi $v0, $0, 1 #return legal move
  	j doneWithVerifyMove
  	
  	invalidRowSize:
  	addi $v0, $0, -1 #return invalid row size
  	j doneWithVerifyMove
  	
  	emptyPocket:
  	addi $v0, $0, 0 #return empty pocket
  	j doneWithVerifyMove
  	
  	distanceInvalid:
  	addi $v0, $0, -2 #return distance invalid
  	j doneWithVerifyMove
  	
  	switchTurns: #switching turns
  	lb $t0 5($s0)
  	lb $t1 4($s0)
  	addi $t1, $t1, 1
  	sb $t1 4($s0)
  	li $t1, 'B'
  	li $t2, 'T'
  	beq $t0, $t1, switchToT #if turn is B, branch
  	sb $t1, 5($s0) #switch to B
  	addi $v0, $0, 2
  	j doneWithVerifyMove
  	
  	switchToT:  #switch to T
  	sb $t2, 5($s0)
  	addi $v0, $0, 2
  	j doneWithVerifyMove
	
	doneWithVerifyMove: 
	jr  $ra
execute_move: #execute move 
	add $s0, $0, $a0
	add $s1, $0, $a1
	lb $t0 4($s0)
	addi $t0, $t0, 1
	sb $t0 4($s0)
	lb $t0 5($s0)
	addi $sp, $sp, -12  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	add $s3, $0, $v0 
  	addi $sp, $sp, 12
  	lb $t0 5($s0)
  	addi $sp, $sp, -16  #set pocket to 00
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
	addi $a3, $0, 0
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s3, 12($sp)
  	jal set_pocket
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s3, 12($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 16
  	lb $t0 5($s0) #check to see which player is moving
 	li $t1, 'B'
 	li $t2, 'T'
 	li $t3, 0
 	beq $t0, $t1,startAtB #if player is B, branch
 	j startAtT #jump to start at T
 	
 	fromTtoB:
 	addi $s1, $0, 5
 	j loopThruB
 	startAtB: #starts at B row
 	addi $s1, $s1, -1
 	loopThruB: #looping through B row
 	li $t0, 'B'
 	beqz $s3, checkToSeeWhichPlayerBot
 	addi $sp, $sp, -16  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $t3, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $t3, 12($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 16
  	bltz $s4, checkToSeeCurrentPlayerBot #if you reach past the rows, branch out
  	li $t0, 'B'
  	addi $s4, $s4, 1 #add 1 to the pocket
	addi $sp, $sp, -20  #store new number into pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
	add $a3, $0, $s4
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s3, 12($sp)
  	sw $t3, 16($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s3, 12($sp)
  	lw $t3, 16($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 20
  	addi $s3, $s3, -1 #decrement counter
  	addi $s1, $s1, -1 #decrement distance
 	j loopThruB
 	
 	fromBtoT:
 	addi $s1, $0, 5
 	j loopThruT
 	startAtT: #starts at B row
 	addi $s1, $s1, -1
 	loopThruT: #loop through T row
 	li $t0, 'T'
 	beqz $s3, checkToSeeWhichPlayerTop
 	addi $sp, $sp, -16  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $t3, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $t3, 12($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 16
  	bltz $s4, checkToSeeCurrentPlayerTop #if you reach past the rows, branch out
  	li $t0, 'T'  
  	addi $s4, $s4, 1 #add 1 to the pocket
	addi $sp, $sp, -20  #store new number into pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
	add $a3, $0, $s4
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s3, 12($sp)
  	sw $t3, 16($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s3, 12($sp)
  	lw $t3, 16($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 20
  	addi $s3, $s3, -1 #decrement counter
  	addi $s1, $s1, -1 #decrement distance
 	j loopThruT
 	
 	checkToSeeCurrentPlayerBot: #checks to see what the current player is on bot side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, addOneToMancala #branch if current character is B
	j fromBtoT #jump to wrap around T
 	
 	checkToSeeWhichPlayerBot: #checks to see what the current player is on bot side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, checkToSeeIfEmpty #branch if current character is B
 	j returnAnywhereElse #jump to return anywhere else
 	
 	checkToSeeCurrentPlayerTop: #checks to see what the current player is on top side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t2, addOneToMancala #branch if current character is T
 	j fromTtoB #jump to wrap around B
 	
 	checkToSeeWhichPlayerTop:  #checks to see what the current player is on top side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t2, checkToSeeIfEmpty #branch if current character is T
 	j returnAnywhereElse #jump to return anywhere else
 	
 	addOneToMancala:  #adds 1 to Mancala
 	addi $sp, $sp, -20  #adding 1 to mancala
	add $a0, $0, $s0 
	add $a1, $0, $t0
	addi $a2, $0, 1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s3, 12($sp)
  	sw $t3, 16($sp)
  	jal collect_stones 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s3, 12($sp)
  	lw $t3, 16($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 20
  	addi $s3, $s3, -1
  	addi $t3, $t3, 1 
  	beqz $s3 handleMancala #if last stone lands on mancala, branch
  	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, fromBtoT #branch if current player is B
 	j fromTtoB #branch if current player is T
 	
 	checkToSeeIfEmpty:	
 	lb $t0 5($s0)
 	addi $s1, $s1, 1
 	addi $sp, $sp, -16  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $t3, 4($sp)
  	sw $s0, 8($sp)
  	sw $s1, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $t3, 4($sp)
  	lw $s0, 8($sp)
  	lw $s1, 12($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 16
  	addi $s4, $s4, -1
  	beqz $s4, emptyMancala #branch if mancala is empty before adding
  	j returnAnywhereElse #branch if mancala is not empty before adding
 	
 	returnAnywhereElse: #return anywhere else  	
 	lb $t0, 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, switchToTAE
 	sb $t1, 5($s0)
 	returnAE:
 	add $v0, $0, $t3
  	addi $v1, $0, 0
  	j doneWithExecuteMoves
  	
  	switchToTAE: #switch to T player
  	sb $t2, 5($s0)
  	j returnAE
  	
  	switchToTEM: #switch to T player
  	sb $t2, 5($s0)
  	j returnTEM
 	
 	emptyMancala: #return when mancala is empty before the adding
 	lb $t0, 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, switchToTEM
 	sb $t1, 5($s0)
 	returnTEM:
 	add $v0, $0, $t3
  	addi $v1, $0, 1
 	j doneWithExecuteMoves
  	
  	handleMancala: #return when the last stone lands on the mancala 
  	add $v0, $0, $t3 
  	addi $v1, $0, 2
  	j doneWithExecuteMoves
  	
	doneWithExecuteMoves:
	jr $ra
steal: #steal mancala
	add $s0, $0, $a0
	add $s1, $0, $a1
	li $s2, 0 #sum
	lb $s3, 2($s0)
	lb $t0, 5($s0)
	addi $s3, $s3, -1
	li $s2, 0
	li $t1, 'B'
	li $t2, 'T'
	beq $t0, $t2, getBotPocket
	j getTopPocket
	
	getDiffBot:
	sub $s1, $s3, $s1 #subtract difference of current position and total 
	getBotPocket:
	addi $sp, $sp, -20  #get the num stones at pocket on the current player's side
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s2, 12($sp)
  	sw $s3, 16($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s2, 12($sp)
  	lw $s3, 16($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 20
	add $s2, $s2, $s4 #adding to total
	addi $sp, $sp, -20  #empty pockets
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $s1
	addi $a3, $0, 0
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s2, 12($sp)
  	sw $s3, 16($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s2, 12($sp)
  	lw $s3, 16($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 20
	lb $t0, 5($s0)
	li $t1, 'B'
	li $t2, 'T'
	beq $t0, $t2, getDiffTop
	addi $sp, $sp, -4  #adding total to top mancala
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s2
  	sw $ra, 0($sp)
  	jal collect_stones 
  	lw $ra, 0($sp)
  	addi $sp, $sp, 4
  	add $s4, $0, $v0
  	j doneWithSteal
	
	getDiffTop:
	sub $s1, $s3, $s1 #subtract difference of current position and total 
	getTopPocket:
	addi $sp, $sp, -20  #get the num stones at pocket on the current player's side
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s2, 12($sp)
  	sw $s3, 16($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s2, 12($sp)
  	lw $s3, 16($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 20
	add $s2, $s2, $s4 #adding to total
	addi $sp, $sp, -20  #empty pockets
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s1
	addi $a3, $0, 0
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s2, 12($sp)
  	sw $s3, 16($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s2, 12($sp)
  	lw $s3, 16($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 20
	lb $t0, 5($s0)
	li $t1, 'B'
	li $t2, 'T'
	beq $t0, $t1, getDiffBot
	addi $sp, $sp, -4  #adding total to top mancala
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $s2
  	sw $ra, 0($sp)
  	jal collect_stones 
  	lw $ra, 0($sp)
  	addi $sp, $sp, 4
  	add $s4, $0, $v0
  	j doneWithSteal
  	
	doneWithSteal:
	jr $ra
	
check_row: #check row
	add $s0, $0, $a0
	li $s1, 0
	li $s2, 5
	checkTopRow: #checking the top row
	bltz $s2, switchToBot #if reach the end, branch
	addi $sp, $sp, -16  #get the num stones at pocket on the opposide side
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s2
  	sw $ra, 0($sp) #grab value at pocket
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s2, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s2, 12($sp)
  	addi $sp, $sp, 16
  	add $s4, $0, $v0
  	add $s1, $s1, $s4 #add to total
	addi $s2, $s2, -1
	j checkTopRow
	
	switchToBot: #switching to bot row
	li $s3, 0
	li $s2, 5
	checkBotRow: #checking the bot row 
	bltz $s2, checkTopAndBot #if reach the end, branch out
	addi $sp, $sp, -16  #get the num stones at pocket on the bot side
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $s2
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $s2, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $s2, 12($sp)
  	addi $sp, $sp, 16
  	add $s4, $0, $v0 
  	add $s3, $s3, $s4 #adding to total
	addi $s2, $s2, -1
	j checkBotRow
	
	checkTopAndBot: #compares top and bot rows
	bnez $s1, checkBottom #if top isn't zero, branch
	addi $sp, $sp, -8  #adding total to bot mancala
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $s3
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	jal collect_stones 
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 8	
  	li $s2, 5
  	emptyTopPocket:
  	bltz $s2, returnEmpty #branch if no more pockets to empty
  	addi $sp, $sp, -12  #empty pockets
	add $a0, $0, $s0 
	addi $a1, $0, 'B'
	add $a2, $0, $s2
	addi $a3, $0, 0
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	sw $s2, 8($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	lw $s2, 8($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 12
  	addi $s2, $s2, -1
  	j emptyTopPocket
	
	checkBottom: #checks the bot row values
	bnez $s3, returnNotEmpty #if bottom isn't zero, branch
	sb $s3, 1($s0)
	addi $sp, $sp, -8  #adding total to top mancala
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	jal collect_stones 
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	add $s4, $0, $v0
  	addi $sp, $sp, 8
  	li $s2, 5
  	emptyBotPocket:
  	bltz $s2, returnEmpty #branch if no more pockets to empty
  	addi $sp, $sp, -12  #empty pockets
	add $a0, $0, $s0 
	addi $a1, $0, 'T'
	add $a2, $0, $s2
	addi $a3, $0, 0
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	sw $s2, 8($sp)
  	jal set_pocket 
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	lw $s2, 8($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 12
  	addi $s2, $s2, -1
  	j emptyBotPocket
	
	returnEmpty:
	li $t8, 'D'
	sb $t8, 5($s0)
	lb $t0 0($s0) #getting player 1's mancalas
	lb $t1 1($s0) #getting player 2's mancalas
	bgt $t0, $t1, playerOneGreater #branch if player 1's mancala is greater than player 2's
	beq $t0, $t1, tie #branch if player 1's mancala is equal to player 2's
	addi $v0, $0, 1 #return player 2's mancala is greater than player 1's
	addi $v1, $0, 2
	j doneWithCheckRow
	
	tie: #return if tie
	addi $v0, $0, 1
	addi $v1, $0, 0
	j doneWithCheckRow
	
	playerOneGreater: #return if player 1's mancala greater than player 2's mancala
	addi $v0, $0, 1
	addi $v1, $0, 1
	j doneWithCheckRow
	
	returnNotEmpty: #if both pockets not empty
	lb $t0 0($s0) #getting player 1's mancalas
	lb $t1 1($s0) #getting player 2's mancalas
	bgt $t0, $t1, playerOneGreaterNE #branch if player 1's mancala is greater than player 2's
	beq $t0, $t1, tieNE #branch if player 1's mancala is equal to player 2's
	addi $v0, $0, 0 #return player 2's mancala is greater than player 1's
	addi $v1, $0, 2
	j doneWithCheckRow
	
	playerOneGreaterNE: #return if player 1's mancala greater than player 2's mancala, but both pockets not empty
	addi $v0, $0, 0
	addi $v1, $0, 1
	j doneWithCheckRow
	
	tieNE: #return if tie, but both pockets not empty
	addi $v0, $0, 0
	addi $v1, $0, 0
	j doneWithCheckRow
	
	doneWithCheckRow:
	jr $ra
load_moves: #load moves
	add $s0, $0, $a0
	add $s1, $0, $a1
	li $v0, 13 #opening file
	move $a0, $s1
	li $a1, 0
	li $a2, 0
	syscall
	add $s2, $0, $v0 #grabs file descriptor
	li $s3, -1 
	beq $s3, $s2, notRealMoveFile #if file is not a real file, branch out
	addi $sp, $sp, -4
	li $s4, 10
	li $s5, 13
	li $s1, 0
	li $t8, 2
	li $t1, 2
	li $s7, 0

	loopThruFirstTwo: #loops through first row of file
	beqz $t8, moveToNextRow
	li $v0, 14 #reading file
	move $a0, $s2
	move $a1, $sp
	li $a2, 1
	syscall	
	lb $s6, 0($sp) #reads the character from the file
	beq $s6, $s5, windowsLoadMoves #if character equal to /r, branch out
	beq $s6, $s4, otherLoadMoves #if character equal to /n, branch out
	addi $s6, $s6, -48 #parses the ascii number 
	mult $s4, $s1
	mflo $t2
	add $s1, $t2, $s6 #adds to total
	j loopThruFirstTwo
	
	windowsLoadMoves: #if reading in windows
	beq $t8, $t1, storeInFirstWindows
	add $t6, $0, $s1 #storing rows
	continueLoadingWindows:
	li $v0, 14 #skip over the /r/n in windows
	move $a0, $s2
	move $a1, $sp
	li $a2, 1
	syscall
	addi $s1,$0, 0 #reset total to 0
	addi $t8, $t8, -1 #decrement counter
	j loopThruFirstTwo
	
	otherLoadMoves: #if reading in other
	beq $t8, $t1, storeInFirstOther
	add $t6, $0, $s1 #storing rows
	continueLoadingOther:
	addi $s1, $0, 0 #reset total to 0
	addi $t8, $t8, -1 #decrement counter
	j loopThruFirstTwo
	
	storeInFirstWindows: #storing if windows
	add $t7, $0, $s1 #storing columns
	j continueLoadingWindows
	
	storeInFirstOther: #storing if other
	add $t7, $0, $s1 #storing columns
	j continueLoadingOther
	
	moveToNextRow: #moving to next row
	li $t5, '0'
	li $t3, '9'
	li $s7, 0
	add $t0, $0, $t7
	li $s1, 10
	li $s5, 13
	loopThroughArray: #looping through the array
	beqz $t6, closeLoadFile	
	beqz $t0, insert99 #if end of colummns, branch
	li $v0, 14 #reading file
	move $a0, $s2
	move $a1, $sp
	li $a2, 2
	syscall
	add $t2, $0, $v0
	beqz $t2, closeLoadFile #branch if end of file
	lb $s6, 0($sp) #reads the character from the file
	lb $s3, 1($sp)
	beq $s6, $s5, closeLoadFile
	beq $s6, $s1, closeLoadFile
	blt $s6, $t5, inputNotValid #branch if character not a number
	bgt $s6, $t3, inputNotValid #branch if character not a number
	blt $s3, $t5, inputNotValid #branch if character not a number
	bgt $s3, $t3, inputNotValid #branch if character not a number
	addi $s6, $s6, -48 #parse number
	addi $s3, $s3, -48
	mult $s4, $s6
	mflo $t1
	add $t4, $t1, $s3
	sb $t4 0($s0) #store number into array
	addi $s0, $s0, 1 #increase pointer
	addi $s7, $s7, 1 #increase move counter
	addi $t0, $t0, -1 #decrease column ounter
	j loopThroughArray
	
	insert99: #insert 99 after every row
	li $t1, 99
	li $t2, 1
	bne $t6, $t2, continueToInsert #branch if not equal to last row
	addi $t6, $t6, -1 #decrease row counter
	j loopThroughArray
	continueToInsert:
	sb $t1, 0($s0) #stores 99 
	addi $s0, $s0, 1 #advance pointer
	add $t0, $0, $t7 #reload columns
	addi $s7, $s7, 1 #increase move counter
	addi $t6, $t6, -1 #decrease row counter
	j loopThroughArray
	
	inputNotValid: #when character is not a number
	li $t1, -1 
	sb $t1 0($s0) #store invalid char into arrays
	addi $s0, $s0, 1 #increase pointer
	addi $s7, $s7, 1 #increase move counter
	addi $t0, $t0, -1 #decrease column ounter
	j loopThroughArray
	
	notRealMoveFile: #return when not a real file
	addi $v0, $0, -1
	j doneWithLoadMoves
	
	closeLoadFile: #close file
	addi $sp, $sp, 4
	li $v0, 16 
	move $a0, $s2 
	syscall 
	add $v0, $0, $s7
	
	doneWithLoadMoves:
	jr $ra
play_game:
	add $s0, $0, $a0 
	add $s1, $0, $a1
	add $s2, $0, $a2
	add $s3, $0, $a3
	lb $s4 0($sp)
	addi $sp, $sp, -20  #call load_game
	add $a0, $0, $s2 
	add $a1, $0, $s1
  	sw $ra, 0($sp)
  	sw $s0, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $s2, 16($sp)
  	jal load_game
  	lw $ra, 0($sp)
  	lw $s0, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $s2, 16($sp)
  	add $t0, $0, $v0 
  	add $t1, $0, $v1
  	addi $sp, $sp, 20
  	bltz $t0, returnErrorFile #branch if error
  	bltz $t1, returnErrorFile #branch if error
  	beqz $t0, returnErrorFile #branch if error
  	beqz $t1, returnErrorFile #branch if error
  	addi $sp, $sp, -16  #call load_moves
	add $a0, $0, $s3 
	add $a1, $0, $s0
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	jal load_moves
  	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	add $s5, $0, $v0 
  	addi $sp, $sp, 16  
  	blt $s4, $s5, goOffNumOfMove
  	add $s0, $0, $s5
  	returnToCheck:	
  	bltz $s5, returnErrorFile #branch if error
  	beqz $s4, checkGame #branch if num_executed moves less than or equal to 0
  	bltz $s4, checkGame
  	loopThroughGame: #loop through moves
  	beqz $s5, checkGame #branch if done
	lb $t1, 5($s2)
	li $t2, 99
	lb $t0, 0($s3)
	beq $t0, $t2, skipToVerify #branch if move[i] = 99
	bltz $t0, skipMove #branch if not valid move
	addi $sp, $sp, -28  #get the num stones at moves[i]
	add $a0, $0, $s2 
	add $a1, $0, $t1
	add $a2, $0, $t0
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $t0, 16($sp)
  	sw $s5, 20($sp)
  	sw $s0, 24($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $t0, 16($sp)
  	lw $s5, 20($sp)
  	lw $s0, 24($sp)
  	add $s6, $0, $v0 
  	addi $sp, $sp, 28
  	bltz $s6, returnErrorFile #branch if error
	addi $sp, $sp, -28  #call verify_moves
	add $a0, $0, $s2 
	add $a1, $0, $t0
	add $a2, $0, $s6
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $t0, 16($sp)
  	sw $s5, 20($sp)
  	sw $s0, 24($sp)
  	jal verify_move 
  	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $t0, 16($sp)
  	lw $s5, 20($sp)
  	lw $s0, 24($sp)
  	add $s1, $0, $v0 
  	addi $sp, $sp, 28
  	bltz $s1, returnErrorFile #branch if error
  	executeMove:
  	beqz $s0, checkGame #branch if done
  	addi $sp, $sp, -28  #call get_move
	add $a0, $0, $s2 
	add $a1, $0, $t0
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $s5, 16($sp)
  	sw $t0, 20($sp)
  	sw $s0, 24($sp)
  	jal get_move
 	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $s5, 16($sp)
  	lw $t0, 20($sp)
  	lw $s0, 24($sp)
  	add $s6, $0, $v0
  	addi $sp, $sp, 28
  	addi $sp, $sp, -32  #call execute_move
	add $a0, $0, $s2 
	add $a1, $0, $t0
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $s5, 16($sp)
  	sw $t0, 20($sp)
  	sw $s0, 24($sp)
  	sw $s6, 28($sp)
  	jal execute_move
 	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $s5, 16($sp)
  	lw $t0, 20($sp)
  	lw $s0, 24($sp)
  	lw $s6, 28($sp)
  	add $s7, $0 ,$v1
  	addi $sp, $sp, 32
  	li $t8, 2
  	beq $s7, $t8, moveToNextMove #branch if landed on own mancala
  	addi $t8, $t8, -1
  	beq $s7, $t8, stealMove #branch if landed on empty mancala before adding
  	addi $s5, $s5, -1
  	addi $s4, $s4, -1
  	addi $s3, $s3, 1
  	j loopThroughGame
  	moveToNextMove:
  	addi $s5, $s5, -1
  	addi $s4, $s4, -1
  	addi $s3, $s3, 1
  	j loopThroughGame
  	
  	goOffNumOfMove:
  	add $s0, $0, $s4
  	j returnToCheck
  	
  	stealMove: #handle when stealing  	
  	addi $sp, $sp, -24  #call steal
	add $a0, $0, $s2 
	add $a1, $0, $s6
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $s5, 16($sp)
  	sw $s0, 20($sp)
  	jal steal
  	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $s5, 16($sp)
  	lw $s0, 20($sp)
  	add $s6, $0, $v0 
  	addi $sp, $sp, 24
  	addi $s5, $s5, -1
  	addi $s4, $s4, -1
  	addi $s3, $s3, 1
  	j loopThroughGame 
  	
  	handleMoving:
  	j doneWithPlayGame
  	
  	skipToVerify: #handles the 99 distance case
  	addi $sp, $sp, -24  #call verify_moves
	add $a0, $0, $s2 
	addi $a1, $0, 0
	add $a2, $0, $t0
  	sw $ra, 0($sp)
  	sw $s2, 4($sp)
  	sw $s3, 8($sp)
  	sw $s4, 12($sp)
  	sw $s5, 16($sp)
  	sw $s0, 20($sp)
  	jal verify_move 
  	lw $ra, 0($sp)
  	lw $s2, 4($sp)
  	lw $s3, 8($sp)
  	lw $s4, 12($sp)
  	lw $s5, 16($sp)
  	lw $s0, 20($sp)
  	add $s6, $0, $v0 
  	addi $sp, $sp, 24
  	bltz $s6, returnErrorFile #branch if error
  	addi $s5, $s5, -1
  	addi $s4, $s4, -1
  	addi $s3, $s3, 1
  	j loopThroughGame #skip back to game
  	
  	checkGame: #check the game
  	addi $sp, $sp, -20 #call check_row
	add $a0, $0, $s2 
  	sw $ra, 0($sp)
  	sw $s4, 4($sp)
  	sw $s5, 8($sp)
  	sw $s0, 12($sp)
  	sw $s2, 16($sp)
  	jal check_row
  	lw $ra, 0($sp)
  	lw $s4, 4($sp)
  	lw $s5, 8($sp)
  	lw $s0, 12($sp)
  	lw $s2, 16($sp)
  	add $t0, $0, $v0
  	add $t1, $0, $v1
  	addi $sp, $sp, 20
  	beqz $t0, returnGameNotDone #branch if check row is not empty
  	beqz $t1, tieDone
  	li $t2, 1
  	beq $t1, $t2, playerOneWon
	addi $v0, $0, 2
  	add $v1, $0, $s0
  	j doneWithPlayGame 
  	
  	tieDone: #return when tie
  	addi $v0, $0, 0
  	add $v1, $0, $s0
  	j doneWithPlayGame
  	
  	playerOneWon:  #return when player one won
  	addi $v0, $0, 1
  	add $v1, $0, $s0
  	j doneWithPlayGame
  	
  	skipMove: #handle when skipping a move
  	addi $s0, $s0, -1
  	addi $s5, $s5, -1
  	addi $s4, $s4, -1
  	addi $s3, $s3, 1
  	j loopThroughGame
 
  	returnGameNotDone: #return when game is not done
  	bltz $s4, returnZero
  	addi $v0, $0, 0
  	add $v1, $0, $s0
  	j doneWithPlayGame
  	
  	returnZero: #return when nobody won and no moves
  	addi $v0, $0, 0
  	addi $v0, $0, 0
  	j doneWithPlayGame
  	
  	returnErrorFile: #return when errors pop up
  	addi $v0, $0, -1
  	addi $v1, $0, -1
  	j doneWithPlayGame

	doneWithPlayGame:
	jr $ra
	
get_move: #get move
	add $s0, $0, $a0
	add $s1, $0, $a1
	lb $t0 5($s0)
	
	addi $sp, $sp, -12  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	add $s3, $0, $v0 
 
  	
  	addi $sp, $sp, 12
  	lb $t0 5($s0) #check to see which player is moving
 	li $t1, 'B'
 	li $t2, 'T'
 	li $t3, 0
 	beq $t0, $t1,startAtBGetMove #if player is B, branch
 	j startAtTGetMove #jump to start at T
 	
 	fromTtoBGetMove:
 	addi $s1, $0, 5
 	j loopThruBGetMove
 	startAtBGetMove: #starts at B row
 	addi $s1, $s1, -1
 	loopThruBGetMove: #looping through B row
 	li $t0, 'B'
 	beqz $s3, checkToSeeWhichPlayerBotGetMove
 	addi $sp, $sp, -16  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $t3, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $t3, 12($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 16
  	bltz $s4, checkToSeeCurrentPlayerBotGetMove #if you reach past the rows, branch out
  	addi $s3, $s3, -1 #decrement counter
  	addi $s1, $s1, -1 #decrement distance
 	j loopThruBGetMove
 	
 	fromBtoTGetMove:
 	addi $s1, $0, 5
 	j loopThruTGetMove
 	startAtTGetMove: #starts at B row
 	addi $s1, $s1, -1
 	loopThruTGetMove: #loop through T row
 	li $t0, 'T'
 	beqz $s3, checkToSeeWhichPlayerTopGetMove
 	addi $sp, $sp, -16  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $s1, 4($sp)
  	sw $s0, 8($sp)
  	sw $t3, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $s1, 4($sp)
  	lw $s0, 8($sp)
  	lw $t3, 12($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 16
  	bltz $s4, checkToSeeCurrentPlayerTopGetMove #if you reach past the rows, branch out
  	addi $s3, $s3, -1 #decrement counter
  	addi $s1, $s1, -1 #decrement distance
 	j loopThruTGetMove
 	
 	checkToSeeCurrentPlayerBotGetMove: #checks to see what the current player is on bot side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, addOneToMancalaGetMove #branch if current character is B
	j fromBtoTGetMove #jump to wrap around T
 	
 	checkToSeeWhichPlayerBotGetMove: #checks to see what the current player is on bot side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, checkToSeeIfEmptyGetMove #branch if current character is B
 	j returnAnywhereElseGetMove #jump to return anywhere else
 	
 	checkToSeeCurrentPlayerTopGetMove: #checks to see what the current player is on top side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t2, addOneToMancalaGetMove #branch if current character is T
 	j fromTtoBGetMove #jump to wrap around B
 	
 	checkToSeeWhichPlayerTopGetMove:  #checks to see what the current player is on top side
 	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t2, checkToSeeIfEmptyGetMove #branch if current character is T
 	j returnAnywhereElseGetMove #jump to return anywhere else
 	
 	addOneToMancalaGetMove:  #adds 1 to Mancala
  	addi $s3, $s3, -1
  	addi $t3, $t3, 1 
  	beqz $s3 handleMancala #if last stone lands on mancala, branch
  	lb $t0 5($s0)
 	li $t1, 'B'
 	li $t2, 'T'
 	beq $t0, $t1, fromBtoTGetMove #branch if current player is B
 	j fromTtoBGetMove #branch if current player is T
 	
 	checkToSeeIfEmptyGetMove:		
 	lb $t0 5($s0)
 	addi $s1, $s1, 1
 	addi $sp, $sp, -16  #get the num stones at pocket
	add $a0, $0, $s0 
	add $a1, $0, $t0
	add $a2, $0, $s1
  	sw $ra, 0($sp)
  	sw $t3, 4($sp)
  	sw $s0, 8($sp)
  	sw $s1, 12($sp)
  	jal get_pocket 
  	lw $ra, 0($sp)
  	lw $t3, 4($sp)
  	lw $s0, 8($sp)
  	lw $s1, 12($sp)
  	add $s4, $0, $v0 
  	addi $sp, $sp, 16
  	addi $s4, $s4, -1
  	beqz $s4, emptyMancalaGetMove #branch if mancala is empty before adding
  	j returnAnywhereElseGetMove #branch if mancala is not empty before adding
 	
 	returnAnywhereElseGetMove: #return anywhere else  	
 	add $v0, $0, $s1
  	j doneWithGetMove
  	
 	emptyMancalaGetMove: #return when mancala is empty before the adding
 	add $v0, $0, $s1
 	j doneWithGetMove
  	
  	handleMancalaGetMove: #return when the last stone lands on the mancala 
  	add $v0, $0, $s1
  	j doneWithGetMove
  	
	doneWithGetMove:
	jr $ra
		
print_board: #printing board
	add $s0, $0, $a0
	lb $s1, 2($s0) #storing number of pockets
	addi $s0, $s0, 6
	lb $t0 0($s0)  #printing top mancala
	lb $t1,1($s0)
	li $v0, 11
	move $a0, $t0
	syscall
	li $v0, 11
	move $a0, $t1
	syscall
	li $t0, '\n' #printing new line
	li $v0, 11
	move $a0, $t0
	syscall
	sll $s1, $s1, 1 #getting address between top/bot rows
	add $s3, $s0, $s1
	addi $s3, $s3, 2
	sll $s1, $s1, 1
	add $s2, $s0, $s1 #getting address of bot mancala
	addi $s2, $s2, 2
	lb $t0 0($s2) #printing bot mancala
	lb $t1,1($s2)
	li $v0, 11
	move $a0, $t0
	syscall
	li $v0, 11
	move $a0, $t1
	syscall
	li $t0, '\n' #printing new line
	li $v0, 11
	move $a0, $t0
	syscall
	continueWithPrinting: #continue printing second row
	addi $s0, $s0, 2 
	loopThruString: #loop through board and print 
	beq $s0, $s2, doneWithPrintBoard #if at the end, branch
	beq $s0, $s3, printNewLine #if between bot/top rows, branch
	lb $t0 0($s0) #print out characters
	lb $t1,1($s0)
	li $v0, 11
	move $a0, $t0
	syscall
	li $v0, 11
	move $a0, $t1
	syscall
	addi $s0, $s0, 2 #increment counter
	j loopThruString

	printNewLine: #printing new line
	li $t0, '\n'
	li $v0, 11
	move $a0, $t0
	syscall
	lb $t0 0($s0)
	lb $t1,1($s0)
	li $v0, 11
	move $a0, $t0
	syscall
	li $v0, 11
	move $a0, $t1
	syscall
	j continueWithPrinting
	
	doneWithPrintBoard:
	jr $ra
write_board:
	add $s0, $0, $a0
	add $s1, $0, $0
	li $t0, 'o' #build string
	li $t1, 'u'
	li $t2, 't'
	li $t3, 'p'
	li $t4, '.'
	li $t5, 'x'
	li $t6, 0
	addi $sp, $sp, -11
	sb $t0, 0($sp)
	sb $t1, 1($sp)
	sb $t2, 2($sp)
	sb $t3, 3($sp)
	sb $t1, 4($sp)
	sb $t2, 5($sp)
	sb $t4, 6($sp)
	sb $t2, 7($sp)
	sb $t5, 8($sp)
	sb $t2, 9($sp)
	sb $t6, 10($sp)
	add $s1, $0, $sp #store string into register
	addi $sp, $sp, 11
	li $v0, 13 #open file that doesn't exist
	move $a0, $s1
	li $a1, 1
	li $a2, 0
	syscall
	move $s6, $v0
	bltz $s6, writingError #branch if writing to file has error
	addi $sp, $sp, -4
	lb $s1, 2($s0) #storing number of pockets
	addi $s0, $s0, 6
	lb $t0 0($s0)  #printing top mancala
	lb $t1 1($s0)
	sb $t0, 0($sp) #writing to file
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	sb $t1, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	li $t0, '\n' #printing new line
	sb $t0, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	sll $s1, $s1, 1 #getting address between top/bot rows
	add $s3, $s0, $s1
	addi $s3, $s3, 2
	sll $s1, $s1, 1
	add $s2, $s0, $s1 #getting address of bot mancala
	addi $s2, $s2, 2
	lb $t0 0($s2) #printing bot mancala
	lb $t1,1($s2)
	sb $t0, 0($sp) #writing to file
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	sb $t1, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	li $t0, '\n' #printing new line
	sb $t0, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	continueWithWriting: #continue printing second row
	addi $s0, $s0, 2 
	loopThruGameboard: #loop through board and print 
	beq $s0, $s2, doneWithWriteBoard #if at the end, branch
	beq $s0, $s3, printNewLineWB #if between bot/top rows, branch
	lb $t0 0($s0) #print out characters
	lb $t1,1($s0)
	sb $t0, 0($sp) #writing to file
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	sb $t1, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	addi $s0, $s0, 2 #increment counter
	j loopThruGameboard

	printNewLineWB: #printing new line
	li $t0, '\n' #printing new line
	sb $t0, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	lb $t0 0($s0)
	lb $t1,1($s0)
	sb $t0, 0($sp) #writing to file
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	sb $t1, 0($sp)
	li $v0, 15 
	move $a0, $s6
	move $a1, $sp
	li $a2, 1
	syscall
	j continueWithWriting
	
	writingError:
	addi $v0, $0, -1
	j doneWithWB
	
	doneWithWriteBoard:
	addi $sp, $sp, 4
	li $v0, 16 #close file
	move $a0, $s6
	syscall
	addi $v0, $0, 1
	
	doneWithWB:
	jr $ra
	
############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
