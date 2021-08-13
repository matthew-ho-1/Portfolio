.data
coeff: .word 2
exp: .word -3

.text:
main:
    li $s2, 10
    
    lw $a0, coeff
    lw $a1, exp
    jal create_term
   

    #write test code
    add $s0, $0, $v0
    li $v0, 1
    move $a0, $s0
    syscall
    
    li $v0, 11
    li $a0, '\n'
    syscall
    
    li $v0, 1
    move $a0, $s2
    syscall
    

    li $v0, 10
    syscall

.include "hw5.asm"
