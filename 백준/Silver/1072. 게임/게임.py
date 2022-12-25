X, Y = map(int, input().split())

Z = (Y * 100) // X

if Z >= 99:
    print(-1)
    exit(0)

ans = 0
left = 1
right = X

while left <= right:
    mid = (left + right) // 2
    if (Y + mid) * 100 // (X + mid) <= Z:
        left = mid + 1
    else:
        ans = mid
        right = mid - 1

print(ans)
