.data
filename: .asciiz "moves03.txt"
space: .asciiz "\n"
pog: .asciiz "pog"
.align 0
moves: .byte 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
.text
.globl main
main:
la $a0, moves
la $a1, filename
jal load_moves

# You must write your own code here to check the correctness of the function implementation.
add $s0, $0, $v0

li $v0, 1
move $a0, $s0
syscall

li $v0, 10
syscall

.include "hw3.asm"
