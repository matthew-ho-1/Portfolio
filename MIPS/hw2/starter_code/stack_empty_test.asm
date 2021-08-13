.data
Newline: .asciiz "\n"
WrongArgMsg: .asciiz "You must provide exactly one argument"
BadToken: .asciiz "Unrecognized Token"
ParseError: .asciiz "Ill Formed Expression"
ApplyOpError: .asciiz "Operator could not be applied"
Comma: .asciiz ","

val_stack : .word 0
op_stack : .word 0

.text
.globl main
main:
  # add code to call and test stack_empty function
  addi $a0, $0, 0
  jal is_stack_empty
  add $s0, $v0, $0
  li $v0, 1
  move $a0, $s0
  syscall
  
end:
  # Terminates the program
  li $v0, 10
  syscall

.include "hw2-funcs.asm"
