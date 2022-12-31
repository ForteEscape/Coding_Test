N = int(input())
height = list(map(int, input().split()))

data_length = len(height)
start = height[0]
end = -1
ans = 0

for i in range(1, data_length):
    if height[i] > height[i - 1]:
        end = height[i]
    else:
        if end != -1:
            ans = max(ans, end - start)
        start = height[i]
        end = -1

if end != -1:
    ans = max(ans, end - start)

print(ans)