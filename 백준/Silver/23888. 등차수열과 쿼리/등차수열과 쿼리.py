import math
import sys

a, d = map(int, input().split())
q = int(input())

for _ in range(q):
    query, l, r = map(int, sys.stdin.readline().split())

    if query == 1:
        result1 = (r * (2 * a + (r - 1) * d)) // 2
        result2 = ((l - 1) * (2 * a + ((l - 1) - 1) * d)) // 2

        print(result1 - result2)
    elif query == 2:
        if l == r:
            print(a + (l - 1) * d)
        else:
            print(math.gcd(a, d))
