import heapq

N = int(input())
heap = []

key_data = int(input())

if N == 1:
    print(0)
    exit(0)

for i in range(2, N + 1):
    data = int(input())
    heapq.heappush(heap, [-data, data, i])

ans = 0

while True:
    mx_data = heapq.heappop(heap)

    if mx_data[1] < key_data:
        print(ans)
        break

    key_data += 1
    heapq.heappush(heap, [mx_data[0] + 1, mx_data[1] - 1, mx_data[2]])
    ans += 1




