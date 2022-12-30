N = input()

for i in range(len(N) // 2 + 1):
    if N[i] != N[len(N) - i - 1]:
        print(0)
        break
else:
    print(1)