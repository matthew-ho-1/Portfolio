.data
origin_pocket: .byte 0
distance: .byte 99
space: .asciiz "\n"
pog : .asciiz "pog"
.align 2
state:        
    .byte 0         # bot_mancala       	(byte #0)
    .byte 1         # top_mancala       	(byte #1)
    .byte 6         # bot_pockets       	(byte #2)
    .byte 6         # top_pockets        	(byte #3)
    .byte 2         # moves_executed	(byte #4)
    .byte 'T'    # player_turn        		(byte #5)
    # game_board                     		(bytes #6-end)
    .asciiz
    "0108070601000404040404040400"
.text
.globl main
main:
la $a0, state
lb $a1, origin_pocket
lb $a2, distance
jal verify_move
# You must write your own code here to check the correctness of the function implementation.
add $s0, $0, $v0

li $v0, 1
move $a0, $s0
syscall

li $v0, 10
syscall

.include "hw3.asm"
