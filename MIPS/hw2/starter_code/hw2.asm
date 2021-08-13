############################ DO NOT CHANGE THIS FILE! ############################
############################ DO NOT CHANGE THIS FILE! ############################
.data
Newline: .asciiz "\n"
WrongArgMsg: .asciiz "You must provide exactly one argument"
BadToken: .asciiz "Unrecognized Token"
ParseError: .asciiz "Ill Formed Expression"
ApplyOpError: .asciiz "Operator could not be applied"

arg1_addr : .word 0
num_args : .word 0

val_stack : .word 0
op_stack : .word 0

.text
.globl main
main:
	sw $a0, num_args
  li $t0, 1
	bne $a0, $t0, wrong_num_args

	lw $t0, 0($a1)
  sw $t0, arg1_addr
	lw $s1, arg1_addr

  move $a0, $s1
	jal eval
  j end

	wrong_num_args:
		li $v0, 4
  	la $a0, WrongArgMsg
  	syscall

	end:
		# Terminates the program
		li $v0, 10
		syscall

.include "hw2-funcs.asm"
