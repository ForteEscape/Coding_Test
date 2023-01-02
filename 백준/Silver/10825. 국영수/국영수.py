N = int(input())
data = []

for _ in range(N):
    row = list(input().split())
    data.append(row)

data.sort(key=lambda x: (-int(x[1]), int(x[2]), -int(x[3]), x[0]))

for element in data:
    print(element[0])