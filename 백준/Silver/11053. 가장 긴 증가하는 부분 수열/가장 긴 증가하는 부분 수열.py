N = int(input())
seq = list(map(int, input().split()))

# table은 i일때 LIS의 길이이다.
table = [1 for _ in range(N)]

for i in range(N):
    for j in range(i):
        if seq[i] > seq[j]:
            table[i] = max(table[i], table[j] + 1)

print(max(table))