N = int(input())
data = list(map(int, input().split()))
data.sort()

ans = 1
for num in data:
    if ans < num:
        break

    ans += num

print(ans)