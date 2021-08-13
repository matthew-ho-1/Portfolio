.data
Newline: .asciiz "\n"
WrongArgMsg: .asciiz "You must provide exactly one argument"
BadToken: .asciiz "Unrecognized Token"
ParseError: .asciiz "Ill Formed Expression"
ApplyOpError: .asciiz "Operator could not be applied"

val_stack : .word 0
op_stack : .word 0

.text
.globl main
main:

  # add code to call and test eval function

end:
	# Terminates the program
	li $v0, 10
	syscall

.include "hw2-funcs.asm"
