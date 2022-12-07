import sys

N = int(input())
data = 0
for _ in range(N):
    query = sys.stdin.readline().split()

    if query[0] == 'all':
        data = (1 << 21) - 1
    elif query[0] == 'empty':
        data = 0
    else:
        element = int(query[1])

        if query[0] == 'add':
            data |= (1 << element)
        elif query[0] == 'remove':
            data &= ~(1 << element)
        elif query[0] == 'check':
            print(1 if data & (1 << element) != 0 else 0)
        elif query[0] == 'toggle':
            data ^= (1 << element)