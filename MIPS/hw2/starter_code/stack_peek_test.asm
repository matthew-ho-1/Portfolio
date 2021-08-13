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
# add code to call and test test stack_peek function
  la $s0, val_stack
  la $s1, op_stack
  addi $a0, $0, '+'
  addi $a1, $0, 0
  add $a2, $0, $s1
  jal stack_push
  addi $a0, $0, '*'
  addi $a1, $0, 4
  add $a2, $0, $s1
  jal stack_push

  addi $a0, $0, 4
  add $a1, $0, $s1
  jal stack_peek
  add $t0, $0, $v0
  li $v0, 11
  move $a0, $t0
  syscall


end:
  # Terminates the program
  li $v0, 10
  syscall

.include "hw2-funcs.asm"
