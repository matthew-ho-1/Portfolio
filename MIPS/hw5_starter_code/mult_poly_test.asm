.data
p_pair: .word 5 2
p_terms: .word 7 1 0 -1
q_pair: .word -5 2
q_terms: .word 1 2 0 -1
p: .word 0
q: .word 0
r: .word 0
N: .word 3

.text:
main:
li $s2, 10
    la $a0, p
    la $a1, p_pair
    jal init_polynomial

    la $a0, p
    la $a1, p_terms
    lw $a2, N
    jal add_N_terms_to_polynomial

    la $a0, q
    la $a1, q_pair
    jal init_polynomial

    la $a0, q
    la $a1, q_terms
    lw $a2, N
    jal add_N_terms_to_polynomial

    la $a0, p
    la $a1, q
    la $a2, r
    jal mult_poly
    
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
        li $v0, 11
    li $a0, '\n'
    syscall
    
    printPoly:
            la $t0, r
            lw $t1, 0($t0)
            lw $a0, 0($t1)
            li $v0, 1
            syscall
            lw $a0, 4($t1)
            li $v0, 1
            syscall
            lw $a0, 8($t1)
            beqz $a0, end
            la $t0, r
            lw $t2, 8($t1)
            sw $t2, 0($t0)
            j printPoly


    end:li $v0, 10
        syscall

.include "hw5.asm"
