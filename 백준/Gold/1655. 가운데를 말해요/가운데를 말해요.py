import heapq
import sys

N = int(sys.stdin.readline().rstrip())
# 중간값보다 작은 값을 넣는 힙
left_heap = []

# 중간값보다 큰 값을 넣는 힙
right_heap = []

for _ in range(N):
    k = int(sys.stdin.readline().rstrip())

    if len(left_heap) == len(right_heap):
        heapq.heappush(left_heap, -k)
    else:
        heapq.heappush(right_heap, k)

    if right_heap and right_heap[0] < -left_heap[0]:
        left_val = heapq.heappop(left_heap)
        right_val = heapq.heappop(right_heap)

        heapq.heappush(left_heap, -right_val)
        heapq.heappush(right_heap, -left_val)

    print(-left_heap[0])
