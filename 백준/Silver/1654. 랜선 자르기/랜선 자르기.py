N, K = map(int, input().split())
wire_length = []

for _ in range(N):
    wire = int(input())
    wire_length.append(wire)

start = 0
end = max(wire_length)

ans = 0
while start <= end:
    mid = (start + end) // 2
    total_cnt = 0

    if mid == 0:
        mid = 1

    for element in wire_length:
        total_cnt += element // mid

    if total_cnt < K:
        end = mid - 1
    else:
        ans = max(ans, mid)
        start = mid + 1

print(ans)
