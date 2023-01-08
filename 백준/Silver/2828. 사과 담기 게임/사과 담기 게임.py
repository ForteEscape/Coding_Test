import sys

N, M = map(int, input().split())
count = int(input())
data = []
for _ in range(count):
    data.append(int(input()))

dis = 0
start = 1
end = M

for i in range(count):
    if data[i] >= start and data[i] <= M:
        continue
    elif data[i] < start:
        d = start - data[i]
        dis += d
        start -= d
        end -= d
    elif data[i] > end:
        d = data[i] - end
        dis += d
        end += d
        start += d
print(dis)