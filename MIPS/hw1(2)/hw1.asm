.data
ErrMsg: .asciiz "Invalid Argument"
WrongArgMsg: .asciiz "You must provide exactly two arguments"
EvenMsg: .asciiz "Even"
OddMsg: .asciiz "Odd"
binary: .asciiz "--------------------------------"
arg1_addr : .word 0
arg2_addr : .word 0
num_args : .word 0

.text:
.globl main
main:
	sw $a0, num_args

	lw $t0, 0($a1)
	sw $t0, arg1_addr
	lw $s1, arg1_addr

	lw $t1, 4($a1)
	sw $t1, arg2_addr
	lw $s2, arg2_addr

	j start_coding_here

# do not change any line of code above this section
# you can add code to the .data section
start_coding_here:

#Checking to see if there are correct amount of arguments
li $t2, 2
lw $a0, num_args
beq $t2, $a0, firstArgCheck
li $v0, 4
la $a0, WrongArgMsg
syscall 
li $v0, 10
syscall

#Checking to see if the first command-line argument is valid
firstArgCheck:

#Grabbing the first character of the first command-line argument
lw $s3 arg1_addr
lbu $t2, 0($s3)

li $t3, 'O'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is O

li $t3, 'S'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is S

li $t3, 'T'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is T

li $t3, 'I'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is I

li $t3, 'E'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is I

li $t3, 'C'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is C

li $t3, 'X'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is X

li $t3, 'M'
beq $t2, $t3, secondArgCheck #Checking to see if the first command-line argument is M

j errorMsg #If the first command-line argument isn't a valid argument

#Checking to see if the second command-line argument is valid
secondArgCheck:

#Establishing loop counters and limits for seecond-line argument check as well as loading second-line argument check
lw $s3, arg2_addr
li $t4, 0
li $t5, 10

#Looping through second command-line argument to count the number of characters
loopCounter: 
lbu $s4 0($s3)
beqz $s4, atLeast10CharCheck
addi $s3, $s3, 1
addi $t4, $t4, 1
j loopCounter

#Checking to see if the second command-line argument has at least 10 characters
atLeast10CharCheck:
blt $t4, $t5, errorMsg
j correctFormat

#Checking to see if the second command-line argument is in the correct 32-bit hexadecimal format
correctFormat:

#Checks to see if the first character is 0
firstCheck:
lw $s3, arg2_addr #Loads and gets the first character
lbu $s4 0($s3)
li $t6, '0'
bne $s4, $t6, errorMsg
addi $t4, $t4, 1 #increase counter

#Checks to see if the second character is 'x'
secondCheck:
lbu $s4 1($s3) #Gets the second character
li $t6, 'x'
bne $s4, $t6, errorMsg
addi $t4, $t4, 1 #increase counter
addi $s3, $s3, 2 #advance to after the X

#Checks to see if the last 8 characters are in valid hexadecimal format (0-9) and (A-F)
lastCheck:

#Loading the boundary ASCII Values at 9, A, and F
li $t6, '0' 
li $s5, '9'
li $t7, 'A' 
li $s6, 'F'

#Setting the loop counters and limits
li $t4, 2
li $s7, 10
la $s0, binary

#Looping through the last 8 characters to check if they are valid hexadecimal characters $6,7,4,3,0,1  2,5
lastLoopCheck: 
lbu $s4 0($s3) #Grabbing each character
beq $t4, $s7, branchOperation #branch if at the end of string
blt $s4, $t6, errorMsg #less than 0
bgt $s4, $s6, errorMsg #greater than F
ble $s4, $s5, number #less than equal to 9 
bge $s4, $t7, letter #greater than equal to A
j errorMsg

#Converts 0 - 9 to Decimal Value
number:
add $t0, $0, $s4
addi $t1, $s4, -48
j decimalToBinary

#Converts A - F to Decimal Value
letter:
add $t0, $0, $s4
addi $t1, $s4, -55
j decimalToBinary

#Converts the Decimal value to Binary by using and and shifting operations
decimalToBinary:
addi $t2, $0, 0 
addi $t3, $0, 1 
sll $t3, $t3, 3 
addi $t5, $0, 4 

loop:
and $t2, $t1, $t3 
beqz $t2, advanceCounter  

addi $t2, $0, 0 
addi $t2, $0, 1 
j advanceCounter

advanceCounter:
addi $t2, $t2, 48
sb $t2, 0($s0)

srl $t3, $t3, 1 
addi $s0, $s0, 1
addi $t5, $t5, -1
bne $t5, $0, loop
j counter 

counter:
addi $s3, $s3, 1 
addi $t4, $t4, 1 
j lastLoopCheck


#Branching to the correct operation based on first command-line argument
branchOperation:
#Reloading the first command-line argument
lbu $t2, 0($s1) #Reloading the first command-line argument

#Reloading the binary representation
la $s0, binary

li $t3, 'O'
beq $t2, $t3, operationO #Checking to see if the first command-line argument is O

li $t3, 'S'
beq $t2, $t3, operationS #Checking to see if the first command-line argument is S

li $t3, 'T'
beq $t2, $t3, operationT #Checking to see if the first command-line argument is T

li $t3, 'I'
beq $t2, $t3, operationI #Checking to see if the first command-line argument is I

li $t3, 'E'
beq $t2, $t3, operationE #Checking to see if the first command-line argument is E

li $t3, 'C'
beq $t2, $t3, operationC #Checking to see if the first command-line argument is C

li $t3, 'X'
beq $t2, $t3, operationX #Checking to see if the first command-line argument is X

li $t3, 'M'
beq $t2, $t3, operationM #Checking to see if the first command-line argument is M

#Operation O, gets the opcode from the first 6 bits of the binary representation
operationO:
li $t0, 6
li $t1, 32
li $t2, 0
li $t5, 2

#loops through the first 6 binary digits to get the opcode
loopFirst6:
beqz $t0, printDecimalForOpcode
lb $t3, 0($s0)
addi $t3, $t3, -48
mult $t3, $t1
mflo $t4
add $t2, $t2, $t4
div $t1, $t5
mflo $t1
addi $s0, $s0, 1
addi $t0, $t0, -1
j loopFirst6

#prints the decimal of the opcode
printDecimalForOpcode:
li $v0, 1
add $a0, $0, $t2
syscall
j done

#Operation S, gets the rs field from the bits 7 -  11 of the binary representation
operationS:
li $t0, 5
li $t1, 16
li $t2, 0
li $t5, 2
addi $s0, $s0, 6

#loops through bits 7 - 11 to get the rs field 
loop7thru11:
beqz $t0, printDecimalForRS
lb $t3, 0($s0)
addi $t3, $t3, -48
mult $t3, $t1
mflo $t4
add $t2, $t2, $t4
div $t1, $t5
mflo $t1
addi $s0, $s0, 1
addi $t0, $t0, -1
j loop7thru11

#prints the decimal of the rs field
printDecimalForRS:
li $v0, 1
add $a0, $0, $t2
syscall
j done

#Operation T, gets the rt field from the bits 12 -  16 of the binary representation
operationT:
li $t0, 5
li $t1, 16
li $t2, 0
li $t5, 2
addi $s0, $s0, 11

#loops through bits 12 - 16 to get the rt field 
loop12thru16:
beqz $t0, printDecimalForRT
lb $t3, 0($s0)
addi $t3, $t3, -48
mult $t3, $t1
mflo $t4
add $t2, $t2, $t4
div $t1, $t5
mflo $t1
addi $s0, $s0, 1
addi $t0, $t0, -1
j loop12thru16

#prints the decimal of the rt field
printDecimalForRT:
li $v0, 1
add $a0, $0, $t2
syscall
j done

#Operation I, gets the immediate field from the last 16 bits of the binary representation
operationI: 
li $t0, 16
li $t1, 32768
li $t2, 0
li $t5, 2
li $t6, 15
li $t7, 0
li $s4, 1
addi $s0, $s0, 16
lb $t3, 0($s0)
addi $t3, $t3, -48
beqz $t3, calculateDecimal #Checks the sign bit
addi $t7, $0, -1
la $s2, binary
addi $s2, $s2, 31

findLastOne:
lbu $s3 0($s2)
addi $s3, $s3, -48
bnez $s3, ignoreOnes
addi $s2, $s2, -1 
addi $s4, $s4, 1
j findLastOne

#If sign bit is 1, skip to the position that has a 0
ignoreOnes:
beqz $t6, calculateDecimal
lb $t3, 1($s0)
addi $t3, $t3, -48
beqz $t3, calculateDecimal
div $t1, $t5
mflo $t1
addi $s0, $s0, 1
addi $t0, $t0, -1
addi $t6, $t6, -1
j ignoreOnes

#Calculate decimal value from binary
calculateDecimal:
beqz $t0, checkSignBit
lb $t3, 0($s0)
addi $t3, $t3, -48
ble  $t0, $s4, continue
bnez $t7, complementBit

continue:
mult $t3, $t1
mflo $t4
add $t2, $t2, $t4
div $t1, $t5
mflo $t1
addi $s0, $s0, 1
addi $t0, $t0, -1
j calculateDecimal

complementBit:
beqz $t3, switchToOne
addi $t3, $0, 0
j continue

switchToOne:
addi $t3, $0, 1
j continue


#Checks to see if the sign bit was positive or negative and multiplies result accordingly
checkSignBit:
beqz $t7, printDecimalForI
mult $t2, $t7
mflo $t2

#Prints the decimal value of the immediate value
printDecimalForI:
li $v0, 1
add $a0, $0, $t2
syscall
j done

#Operation E, determines whether the binary representation is even or odd
operationE:
addi $s0, $s0, 31
lb $s2, 0($s0)
addi $s2, $s2, -48
bnez $s2, printOdd
li $v0, 4
la $a0, EvenMsg
syscall
j done

printOdd:
li $v0, 4
la $a0, OddMsg
syscall
j done

#Operation C, counts the number of 1s in the binary representation
operationC:
li $t1, 32
li $t2, 0

#loops through the all the bits to count how many 1s
loopToCount1s:
beqz $t1, printHowManyOnes
lb $t3, 0($s0)
addi $t3, $t3, -48
beqz  $t3, advCounter
addi $t2, $t2, 1

advCounter:
addi $s0, $s0, 1
addi $t1, $t1, -1
j loopToCount1s

#prints how many ones there are in the binary representation
printHowManyOnes:
li $v0, 1
add $a0, $0, $t2
syscall
j done

#Operation X, calculates the exponent in decmal form from the IEEE 754 binary representation
operationX:
li $t0, 8
li $t1, 128
li $t2, 0
li $t5, 2
addi $s0, $s0, 1

#loops through bits 2 - 9 to get the 127-excess form of the IEEE 754 binary representation
loop2Thru9:
beqz $t0, printDecimalForExponent
lb $t3, 0($s0)
addi $t3, $t3, -48
mult $t3, $t1
mflo $t4
add $t2, $t2, $t4
div $t1, $t5
mflo $t1
addi $s0, $s0, 1
addi $t0, $t0, -1
j loop2Thru9

#prints the decimal of the opcode
printDecimalForExponent:
addi $t2, $t2, -127
li $v0, 1
add $a0, $0, $t2
syscall
j done

#Operation M, prints the 23-bit mantissa from the binary representation
operationM:
li $t0, 23
li $t1, 9
addi $s0, $s0, 9
#Printing the 1.
addi $t3, $t3, -48
li $v0, 11
addi $a0, $0, 49
syscall 
li $v0, 11
addi $a0, $0, 46
syscall


#prints the 23 bits
grab23bit:
beqz $t0, add0
lb $t3, 0($s0)
li $v0, 11
add $a0, $0, $t3
syscall
addi $s0, $s0, 1
addi $t0, $t0, -1
j grab23bit
 
#prints the 9 zeros at the end
add0: 
beqz $t1, done
li $v0, 11
addi $a0, $0, 48
syscall
addi $t1, $t1, -1
j add0

#If none of the first-line or second-line command arguments are valid, will print out the error message
errorMsg:
li $v0, 4
la $a0, ErrMsg
syscall 
li $v0, 10
syscall

#Closing the program
done:
li $v0, 10
syscall



