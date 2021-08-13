# add test cases to data section
.data
Network:
  .word 5   #total_nodes (bytes 0 - 3)
  .word 10  #total_edges (bytes 4- 7)
  .word 12  #size_of_node (bytes 8 - 11)
  .word 12  #size_of_edge (bytes 12 - 15)
  .word 1   #curr_num_of_nodes (bytes 16 - 19)
  .word 5   #curr_num_of_edges (bytes 20 - 23)
  .asciiz "NAME" # Name property (bytes 24 - 28)
  .asciiz "FRIEND" # FRIEND property (bytes 29 - 35)
   # nodes (bytes 36 - 95)	
  .byte 2 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0	
   # set of edges (bytes 96 - 215)
  .word 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

.text:
main:
	li $v0, 1
	la $a0, Network
	syscall
	li $t0, '\n'
	li $v0, 11
	move $a0, $t0
	syscall
	la $a0, Network
	jal create_person
	#write test code
	add $s0, $0, $v0
	li $v0, 1
	move $a0, $s0
	syscall
	li $v0, 10
	syscall
	
.include "hw4.asm"
