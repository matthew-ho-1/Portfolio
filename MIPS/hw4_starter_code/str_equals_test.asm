# add test cases to data section
.data
str1: .asciiz "Jane Doe"
str2: .asciiz "Jane Doe"

str3: .asciiz "Jane Does"
space: .asciiz "\n"
.text:
main:
	la $a0, str1
	la $a1, str2
	jal str_equals
	#write test code
	add $s0, $0, $v0
	li $v0, 1
	move $a0, $s0
	syscall
	li $v0, 4
	la $a0, space
	syscall
	la $a0, str1
	la $a1, str3
	jal str_equals
	#write test code
	add $s0, $0, $v0
	li $v0, 1
	move $a0, $s0
	syscall

	
	li $v0, 10
	syscall
	
.include "hw4.asm"
