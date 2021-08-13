# add test cases to data section
# Test your code with different Network layouts
# Don't assume that we will use the same layout in all our tests
.data
Name1: .asciiz "Timmy"
Name2: .asciiz "John Doe"
Network:
  .word 5   #total_nodes (bytes 0 - 3)
  .word 10  #total_edges (bytes 4- 7)
  .word 12  #size_of_node (bytes 8 - 11)
  .word 12  #size_of_edge (bytes 12 - 15)
  .word 4   #curr_num_of_nodes (bytes 16 - 19)
  .word 3   #curr_num_of_edges (bytes 20 - 23)
  .asciiz "NAME" # Name property (bytes 24 - 28)
  .asciiz "FRIEND" # FRIEND property (bytes 29 - 35)
   # nodes (bytes 36 - 95)	
  .byte 'T' 'i' 'm' 'm' 'y' 0 0 0 0 0 0 0 'J' 'o' 'h' 'n' ' ' 'D' 'o' 'e' 0 0 0 0 'A' 'l' 'i' ' ' 'T' 'o' 'u' 'r' 'r' 'e' 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 	
   # set of edges (bytes 96 - 215)
  .word 268501044 268501068 1 268501056 268501068 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

.text:
main:
	la $s0, Network
	la $a0, Network
	la $a1, Name1
	la $a2, Name2
	jal is_friend_of_friend
	
	#write test code
	add $s0, $0, $v0
	li $v0, 1
	move $a0, $s0
	syscall
	
	li $v0, 10
	syscall
	
.include "hw4.asm"
