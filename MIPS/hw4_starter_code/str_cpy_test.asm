# add test cases to data section
.data
src: .asciiz "Steve Rogers"
dest: .asciiz ""

.text:
main:
	la $a0, src
	la $a1, dest
	jal str_cpy
	#write test code
	add $s0, $0, $v0
	li $v0, 1
	move $a0, $s0
	syscall
	li $v0, 10
	syscall
	
.include "hw4.asm"
