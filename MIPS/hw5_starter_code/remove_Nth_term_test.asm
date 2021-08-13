.data
pair: .word 12 8
terms: .word 2 2 4 3 5 0 0 -1
p: .word 0
N: .word 3
N1: .word 5

.text:
main:
li $s2, 10
    la $a0, p
    la $a1, pair
    jal init_polynomial

    la $a0, p
    la $a1, terms
    lw $a2, N
    jal add_N_terms_to_polynomial

    la $a0, p
    lw $a1, N1
    jal remove_Nth_term

    #write test code
    add $s0, $0, $v0
    add $s1, $0, $v1
    li $v0, 1
    move $a0, $s0
    syscall
    li $t0, '\n'
    li $v0, 11
    move $a0, $t0
    syscall
    li $v0, 1
    move $a0, $s1
    syscall
    li $v0, 11
    li $a0, '\n'
    syscall
    
    li $v0, 1
    move $a0, $s2
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


.include "hw5.asm"
