.data
Newline: .asciiz "\n"
WrongArgMsg: .asciiz "You must provide exactly one argument"
BadToken: .asciiz "Unrecognized Token"
ParseError: .asciiz "Ill Formed Expression"
ApplyOpError: .asciiz "Operator could not be applied"
Comma: .asciiz ","
Space: .asciiz " "

val_stack : .word 0
op_stack : .word 0

.text
.globl main
main:
# add code to call and test stack_pop function
  la $s0, val_stack
  la $s1, op_stack
    
  addi $a0, $0, '+'
  addi $a1, $0, 0
  add $a2, $0, $s1
  jal stack_push
  
  addi $a0, $0, 0
  add $a1, $0, $s1
  jal stack_pop
  add $t0, $0, $v0
  add $t1, $0, $v1
  li $v0, 1
  move $a0, $t0
  syscall
  li $v0, 4
  la $a0, Newline
  syscall
  li $v0, 11
  move $a0, $t1
  syscall
  

  



end:
  # Terminates the program
  li $v0, 10
  syscall

.include "hw2-funcs.asm"
