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
# add code to call and test stack_push function
  la $s0, val_stack
  la $s1, op_stack
  addi $a0, $0, 1
  addi $a1, $0, 0
  add $a2, $0, $s0
  jal stack_push
  add $t2, $0, $v0
  li $v0, 1
  move $a0, $t2
  syscall
  addi $a0, $0, '+'
  addi $a1, $0, 0
  add $a2, $0, $s1
  jal stack_push
  add $t2, $0, $v0
  li $v0, 1
  move $a0, $t2
  syscall
  addi $a0, $0, 2
  addi $a1, $0, 4
  add $a2, $0, $s0
  jal stack_push
  add $t2, $0, $v0
  li $v0, 1
  move $a0, $t2
  syscall
  addi $a0, $0, '-'
  addi $a1, $0, 4
  add $a2, $0, $s1
  jal stack_push
  add $t2, $0, $v0
  li $v0, 1
  move $a0, $t2
  syscall
  
  li $v0, 4
  la $a0, Newline
  syscall
  lw $t3, 4($s0)
  li $v0, 1
  move $a0, $t3
  syscall
  li $v0, 4
  la $a0, Newline
  syscall
  lw $t3, 2004($s1)
  li $v0, 11
  move $a0, $t3
  syscall 
  
end:
  # Terminates the program
  li $v0, 10
  syscall

.include "hw2-funcs.asm"
