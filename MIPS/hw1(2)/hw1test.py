import argparse
import subprocess
import secrets
import random
import sys


def expected_out(operation, hex_input):
    binary = bin(int(hex_input, 16))[2:]
    binary = binary.zfill(32)
    if operation == 'O':
        return str(int(binary[:6], 2))
    elif operation == 'S':
        return str(int(binary[6:11], 2))
    elif operation == 'T':
        return str(int(binary[11:16], 2))
    elif operation == 'I':
        return str(int(binary[17:], 2)-(2**15)*int(binary[16]))
    elif operation == 'E':
        return 'Odd' if int(binary, 2)%2 else 'Even'
    elif operation == 'C':
        return str(binary.count('1'))
    elif operation == 'X':
        return str(int(binary[1:9], 2)-127)
    else:
        return f'1.{binary[9:]}000000000'


parser = argparse.ArgumentParser(description='MIPS HW 1 test script')
parser.add_argument('MARS', type=str,
                    help='Path to MARS executable')
parser.add_argument('hw', type=str,
                    help='Path to hw 1 file')
parser.add_argument('-n', type=int, default=100,
                    help='Number of tests')
args = parser.parse_args()

ops = ['O', 'S', 'T', 'I', 'E', 'C', 'X', 'M']

for i in range(args.n):
    op = random.choice(ops)
    hex_string = f'0x{secrets.token_hex(4).upper()}'
    correct_out = expected_out(op, hex_string)
    mars_process = subprocess.run(['java', '-jar', args.MARS, '-q', '-g', '--noGui', args.hw, '--argv', op, hex_string], stdout=subprocess.PIPE)
    out = mars_process.stdout
    out = out.decode('ascii').strip()
    if out == correct_out:
        print(f'{i+1}/{args.n}: Success')
    else:
        print(f'Failed on {op}, {hex_string}\n Expected output: {correct_out}\n Received: {out}')
        sys.exit()

print("Passed all tests!")