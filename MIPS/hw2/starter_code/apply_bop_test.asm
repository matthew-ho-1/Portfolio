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
  # add code to test call and test apply_bop function
  addi $a0, $0, 1
  addi $a1, $0, '*'
  addi $a2, $0, -2
  jal apply_bop
  add $s0, $v0, $0
  li $v0, 1
  move $a0, $s0
  syscall
  
j end

end:
  # Terminates the program
  li $v0, 10
  syscall

.include "hw2-funcs.asm"
