.data
pair: .word 12 8
terms: .word 2 3 4 3 5 0 0 -1
p: .word 0
N: .word 3

.text:
main:
li $s4, 10
    la $a0, p
    la $a1, pair
    jal init_polynomial
    
    la $a0, p
    la $a1, terms
    lw $a2, N
    jal add_N_terms_to_polynomial
    
    add $s0, $0, $v0
    li $v0, 1
    move $a0, $s0
    syscall
    li $v0, 11
    li $a0, '\n'
    syscall
    
    li $v0, 1
    move $a0, $s4
    syscall
    
        li $v0, 11
    li $a0, '\n'
    syscall
    
    
    printPoly:
            la $t0, p
            lw $t1, 0($t0)
            lw $a0, 0($t1)
            li $v0, 1
            syscall
            lw $a0, 4($t1)
            li $v0, 1
            syscall
            lw $a0, 8($t1)
            beqz $a0, end
            la $t0, p
            lw $t2, 8($t1)
            sw $t2, 0($t0)
            j printPoly


    end:li $v0, 10
        syscall
    
    #write test code
    li $v0, 10
    syscall
    

.include "hw5.asm"
