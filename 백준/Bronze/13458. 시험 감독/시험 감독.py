N = int(input())
test_data = list(map(int, input().split()))
main, sub = map(int, input().split())
ans = 0

for i in range(N):
    ans += 1
    test_data[i] -= main

    if test_data[i] > 0:
        ans += test_data[i] // sub

        if test_data[i] % sub > 0:
            ans += 1

print(ans)