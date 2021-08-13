.data
origin_pocket: .byte 4
space: .asciiz "\n"
pog: .asciiz "pogs"
.align 2
state:        
    .byte 1        # bot_mancala       	(byte #0)
    .byte 1         # top_mancala       	(byte #1)
    .byte 6         # bot_pockets       	(byte #2)
    .byte 6         # top_pockets        	(byte #3)
    .byte 2         # moves_executed	(byte #4)
    .byte 'B'    # player_turn        		(byte #5)
    # game_board                     		(bytes #6-end)
    .asciiz
    "0106070702010000120207070001"
.text
.globl main
main:
la $a0, state
lb $a1, origin_pocket
jal execute_move
# You must write your own code here to check the correctness of the function implementation.

mult	$t0, $t1			# $t0 * $t1 = Hi and Lo registers
mflo	$t2					# copy Lo to $t2

div	$t0, $t1			# $t0 / $t1
mflo	$t2					# $t2 = floor($t0 / $t1) 
mfhi	$t3					# $t3 = $t0 mod $t1 

add $s0, $0, $v0
add $s1, $0, $v1
li $v0, 4
la $a0, space
syscall
li $v0, 1
move $a0, $s0
syscall
li $v0, 4
la $a0, space
syscall
li $v0, 1
move $a0, $s1
syscall
li $v0, 4
la $a0, space
syscall
lb $s2 0($sp)
li $v0, 1
move $a0, $s2
syscall
li $v0, 4
la $a0, space
syscall

la $a0, state
jal print_board

li $v0, 10
syscall

.include "hw3.asm"
