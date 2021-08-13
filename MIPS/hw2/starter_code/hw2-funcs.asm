############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################
############################ DO NOT CREATE A .data SECTION ############################

############################## Do not .include any files! #############################

.text
eval: #evaluates the expression, printing an error if the expression is invalid
  add $s0, $0, $a0
  li $s1, 0 #val top pointer
  li $s3, 0 #op top pointer
  la $s4, val_stack
  la $s5, op_stack
  li $s6, '('
  li $s7, ')'
  lb $t0, 0($s0)
  beq $t0, $s6, loop
  beq $t0, $s7, loop
  add $a0, $0, $t0 #Checks to see if char is digit
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal valid_ops
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t1, $v0, $0
  bnez $t1, parseErr
  
  loop:
  lb $s2, 0 ($s0)
  beqz $s2, checkIfEmpty #Checks to see if the string has reached the end
  add $a0, $0, $s2 #Checks to see if char is digit
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal is_digit
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t0, $v0, $0
  bnez $t0, checkNextDigit
  add $a0, $0, $s2 #Checks to see if char is an operator
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal valid_ops
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t0, $v0, $0
  bnez $t0, checkOperator  
  beq $s2, $s6, handleLeft
  beq $s2, $s7, handleRight
  j badTokenErr
  
  continue:
  beqz $s2, checkIfEmpty #Checks to see if the string has reached the end
  addi $s0, $s0, 1
  j loop
  
  checkNextDigit: #Checks to see if next char is digit
  addi $t8, $s2, -48
  add $t7, $0, $t8 #sum
  lb $t6, 1($s0)
  beq $t6, $s6, parseErr
  add $a0, $0, $s2 
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal is_digit
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t0, $v0, $0
  beqz $t0, pushNum
  addi $s0, $s0, 1
  li $t8, 1 #counter
  li $t4, 1 #10^nth-place
  li $t5, 10 #base
  
  loopThruDigits: #Loops through the digits 
  lb $t6, 0($s0)
  add $a0, $0, $t6 #Checks to see if next char is digit
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal is_digit
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t0, $v0, $0
  beqz $t0, calculateNum
  mult $t4, $t5
  mflo $t4
  addi $t8, $t8, 1
  addi $s0, $s0, 1
  j loopThruDigits
  
  calculateNum: #Calculates the number to push onto stack
  sub $s0, $s0, $t8
  addi $t7, $0, 0
  loopToCalculate:
  beqz $t8, pushNum
  lb $t0, 0($s0)
  addi $t0, $t0, -48
  mult $t0, $t4
  mflo $t1
  add $t7, $t7, $t1
  div $t4, $t5
  mflo $t4
  addi $s0, $s0, 1
  addi $t8, $t8, -1
  j loopToCalculate
    
  pushNum: #push number onto val_stack
  add $a0, $0, $t7
  add $a1, $0, $s1
  add $a2, $0, $s4
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $0, $v0 
  j loop
  
  checkOperator: #checks the operator
  lb $t8, 1($s0)
  beqz $t8, parseErr #If next character is null, print error
  add $a0, $0, $t8 #Checks to see if char is an operator
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal valid_ops
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t0, $v0, $0
  bnez $t0, parseErr #If next character is operator, print error
  beqz $s3, pushToOpStack  #Checks the precedence of the current character
  addi $t0, $s3, -4
  add $a0, $0, $t0 #Peeks at the top of op_stack
  add $a1, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_peek
  lw $ra, 0($sp)
  addi $sp, $sp, 4 
  add $t6, $v0, $0
  beq $t6, $s6, pushToOpStack
  add $a0, $0, $s2 
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal op_precedence 
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t7, $v0, $0
  addi $t0, $s3, -4
  add $a0, $0, $t0 #Peeks at the top of op_stack
  add $a1, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_peek
  lw $ra, 0($sp)
  addi $sp, $sp, 4 
  add $t6, $v0, $0
  add $a0, $0, $t6  #Checks the precedence of the top of op_stack
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal op_precedence 
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t5, $v0, $0
  bge $t5, $t7, popOffStack

  pushToOpStack: #pushes operator to the stack
  add $a0, $0, $s2
  add $a1, $0, $s3
  add $a2, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s3, $v0, $0 
  j continue
  
  popOffStack:
  addi $s3, $s3, -4
  add $a0, $0, $s3 #pop off the op_stack
  add $a1, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s3, $v0, $0  
  add $t7, $v1, $0
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop once off the val_stack
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t8, $v1, $0 
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop twice off the val_stack
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t2, $v1, $0
  add $a0, $0, $t2 #evaluate 
  add $a1, $0, $t7
  add $a2, $0, $t8
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal apply_bop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t1, $v0, $0 
  add $a0, $0, $t1 #push new value onto stack
  add $a1, $0, $s1
  add $a2, $0, $s4
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $a0, $0, $s2 #push next operator onto stack
  add $a1, $0, $s3
  add $a2, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s3, $v0, $0 
  j continue
  
  handleLeft: #push left parenthesis onto op_stack
  add $a0, $0, $s2
  add $a1, $0, $s3
  add $a2, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s3, $v0, $0 
  j continue
  
  handleRight: #pop off op_stack and val_stack and evaluate
  lb $t0, 1($s0)
  beq $t0, $s6, parseErr
  add $a0, $0, $t0 #Checks to see if char is digit
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal is_digit
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t0, $v0, $0
  bnez $t0, parseErr

  
  handleRightLoop:
  addi $s3, $s3, -4
  add $a0, $0, $s3 #pop off the op_stack
  add $a1, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s3, $v0, $0  
  add $t7, $v1, $0
  beq $t7, $s6, continue
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop once off the val_stack
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t8, $v1, $0 
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop twice off the val_stack
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t2, $v1, $0
  add $a0, $0, $t2 #evaluate 
  add $a1, $0, $t7
  add $a2, $0, $t8
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal apply_bop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t1, $v0, $0 
  add $a0, $0, $t1 #push new value onto stack
  add $a1, $0, $s1
  add $a2, $0, $s4
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  j handleRightLoop
  
  checkIfEmpty: #Check if the stacks are empty (NEED TO CHECK IF OP_STACK EMPTY BUT VAL_STACK NOT)
  addi $t0, $s3, -4
  add $a0, $0, $t0
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal is_stack_empty
  lw $ra, 0($sp)
  addi $sp, $sp, 4 
  add $t8, $v0, $0
  beqz $t8, finalAnswer
  addi $t0, $s1, -4
  add $a0, $0, $t0
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal is_stack_empty
  lw $ra, 0($sp)
  addi $sp, $sp, 4 
  add $t8, $v0, $0
  beqz $t8, finalAnswer
  addi $s3, $s3, -4
  add $a0, $0, $s3 #pop off the op_stack
  add $a1, $0, $s5
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s3, $v0, $0  
  add $t7, $v1, $0
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop once off the val_stack
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t8, $v1, $0 
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop twice off the val_stack
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t2, $v1, $0
  add $a0, $0, $t2 #evaluate 
  add $a1, $0, $t7
  add $a2, $0, $t8
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal apply_bop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $t1, $v0, $0 
  add $a0, $0, $t1 #push new value onto stack
  add $a1, $0, $s1
  add $a2, $0, $s4
  addi $sp, $sp, -4 
  sw $ra, 0($sp)
  jal stack_push
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  j checkIfEmpty
  
  finalAnswer:
  addi $s1, $s1, -4
  add $a0, $0, $s1 #pop once off the val_stack for the last time
  add $a1, $0, $s4
  addi $sp, $sp, -4  
  sw $ra, 0($sp)
  jal stack_pop
  lw $ra, 0($sp)
  addi $sp, $sp, 4
  add $s1, $v0, $0 
  add $t8, $v1, $0 
  li $v0, 1 #print off the final value
  move $a0, $t8
  syscall
  jr $ra
  
  parseErr:
  li $v0, 4
  la $a0, ParseError
  syscall
  li $v0, 10
  syscall
  
  badTokenErr:
  li $v0, 4
  la $a0, BadToken
  syscall
  li $v0, 10
  syscall  
  
is_digit: #Checks to see if value passed is a digit
  add $t0, $0, $a0 
  li $v0, 1 
  li $t1, '0'
  li $t2, '9'
  blt $t0, $t1, notDigit 
  bgt $t0, $t2, notDigit
  addi $v0, $0, 1
  j doneWithDigit
  
  notDigit:
  addi $v0, $0, 0
  
  doneWithDigit:
  jr $ra

stack_push: #pushes the element on the stack
  add $t0, $0, $a0
  add $t1, $0, $a1
  add $t2, $0, $a2
  li $t3, 2000
  la $t4, val_stack
  li $t5, 2004
  bgt $t1, $t5, maxedOutError
  beq $t2, $t4, push
  add $t2, $t2, $t3 #If $t2 is op_stack, add 2000 to the base address
  
  push:
  add $t2, $t2, $t1
  sw $t0, 0($t2)
  addi $t1, $t1, 4
  add $v0, $0, $t1
  jr $ra
  
  maxedOutError: #if the stack reaches the max of 500 elements, print an error
  li $v0, 4
  la $a0, ApplyOpError
  syscall
  li $v0, 10
  syscall
  
stack_peek: #peeks the top element on the stack
  add $t0, $0, $a0
  add $t1, $0, $a1
  li $t3, -4
  li $t5, 2000
  la $t6, val_stack
  ble $t0, $t3, stackEmptyPeek
  beq $t1, $t6, peek
  add $t1, $t1, $t5 #if $t2 is op_stack, add 2000 to the base address
  
  peek:
  add $t1, $t1, $t0
  lw $t4, 0($t1)
  add $v0, $0, $t4
  jr $ra
  
  stackEmptyPeek: #if the stack is empty print error out
  li $v0, 4
  la $a0, ParseError
  syscall
  li $v0, 10
  syscall

stack_pop: #pops the top element on the stack and returns the new top pointer
  add $t0, $0, $a0
  add $t1, $0, $a1
  li $t3, -4
  li $t5, 2000
  la $t6, val_stack
  ble $t0, $t3, stackEmptyPop
  beq $t1, $t6, pop
  add $t1, $t1, $t5 #if $t2 is op_stack, add 2000 to the base address

  pop:
  add $t1, $t1, $t0
  lw $t4, 0($t1)
  add $v0, $0, $t0
  add $v1, $0, $t4
  jr $ra
  
stackEmptyPop: #if the stack is empty print error out
  li $v0, 4
  la $a0, ParseError
  syscall
  li $v0, 10
  syscall

is_stack_empty: #checks to see if the stack is empty
  add $t0, $0, $a0
  li $t1, -1
  bgt $t0, $t1, notEmpty
  addi $v0, $0, 0
  j doneCheck
  
  notEmpty:
  addi $v0, $0, 1
  
  doneCheck:
  jr $ra

valid_ops: #Checks to see if value passed is a valid operator
  add $t0, $0, $a0
  li $t1, '+'
  li $t2, '-'
  li $t3, '*'
  li $t4, '/'
  beq $t0, $t1, isOperator
  beq $t0, $t2, isOperator
  beq $t0, $t3, isOperator
  beq $t0, $t4, isOperator
  addi $v0, $0, 0
  j doneWithOpCheck
  
  isOperator: 
  addi $v0, $0, 1
  
  doneWithOpCheck:
  jr $ra

op_precedence: #Checks the operator in the argument passed for precedence
  add $t0, $0, $a0
  li $t1, '+'
  li $t2, '-'
  li $t3, '*'
  li $t4, '/'
  
  beq $t0, $t1, isAddSub 
  beq $t0, $t2, isAddSub
  beq $t0, $t3, isMultDiv
  beq $t0, $t4, isMultDiv
  li $v0, 4
  la $a0, BadToken
  syscall
  li $v0, 10
  syscall
  
  isAddSub: #Add or subtraction precedence
  addi $v0, $0, 0
  j doneWithOpPrec
  
  isMultDiv: ##Multiply or division precedence
  addi $v0, $0, 1
  
  doneWithOpPrec:
  jr $ra

apply_bop: #Applies the Binary Operation that the arguments give
 add $t0, $0, $a0
 add $t1, $0, $a1
 add $t2, $0, $a2
 li $t3, '+'
 li $t4, '-'
 li $t5, '*'
 li $t6, '/'
 
 beq $t1, $t3, addition
 beq $t1, $t4, subtraction
 beq $t1, $t5, multiply
 beq $t1, $t6, divide
 
 addition: #adds the two numbers
 add $t7, $t0, $t2
 j doneWithOperation
 
 subtraction: #subtracts the two numbers
 sub $t7, $t0, $t2
 j doneWithOperation
 
 multiply: #multiplies the two numbers
 mult $t0, $t2
 mflo $t7
 j doneWithOperation
 
 divide: #divides the two numbers
 beqz $t2, divideBy0 #if second number is 0, print error
 div $t0, $t2
 mflo $t7
 mfhi $t8
 blt $t0, $0, floorDivFirst #if first number is negative, branch to do floor division
 blt $t2, $0, floorDivSecond #if second number is negative, branch to do floor division
 j doneWithOperation
 
 floorDivFirst: #calculating floor division 
 blt $t2, $0, doneWithOperation #if both numbers are negative branch out
 beqz $t8, doneWithOperation #if the remainder is 0, branch out
 addi $t7, $t7, -1 
 j doneWithOperation
 
 floorDivSecond: #calculating floor division
 beqz $t8, doneWithOperation
 addi $t7, $t7, -1
 j doneWithOperation
 
 divideBy0: #printing out error for dividing by 0 and terminating
 li $v0, 4
 la $a0, ApplyOpError
 syscall
 li $v0, 10
 syscall
 
 doneWithOperation:
 add $v0, $0, $t7
 jr $ra
