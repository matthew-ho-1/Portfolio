# add test cases to data section
.data
str1: .asciiz "Ali Tourre"
space: .asciiz "\n"
.text:
main:
	la $a0, str1
	jal str_len
	#write test code
	add $s0, $0, $v0
	li $v0, 1
	move $a0, $s0
	syscall
	li $v0, 10
	syscall
	
	
	
.include "hw4.asm"
