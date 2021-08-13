# add test cases to data section
# Test your code with different Network layouts
# Don't assume that we will use the same layout in all our tests
.data
space: .asciiz "\n"
Network:
  .word 5   #total_nodes (bytes 0 - 3)
  .word 10  #total_edges (bytes 4- 7)
  .word 12  #size_of_node (bytes 8 - 11)
  .word 12  #size_of_edge (bytes 12 - 15)
  .word 1   #curr_num_of_nodes (bytes 16 - 19)
  .word 0   #curr_num_of_edges (bytes 20 - 23)
  .asciiz "NAME" # Name property (bytes 24 - 28)
  .asciiz "FRIEND" # FRIEND property (bytes 29 - 35)
   # nodes (bytes 36 - 95)	
  .byte 65 72 89 92 65 72 89 92 65 72 89 92 65 72 89 92 65 72 89 92 65 72 89 92 65 72 89 92 65 72 89 92 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28	
   # set of edges (bytes 96 - 215)
  .word 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0


.text:
main:
	la $s0, Network
	addi $t0, $s0, 48
	la $a0, Network
	move $a1, $t0
	jal is_person_exists
	#write test code
	add $s1, $0, $v0
	li $v0, 1
	move $a0, $s1
	syscall
	
	li $v0, 10
	syscall
	
.include "hw4.asm"
