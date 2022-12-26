from collections import Counter

N = int(input())
data = []

for _ in range(N):
    book = input()
    data.append(book)

sold_data = dict(Counter(data))

result = []
for key in sold_data:
    result.append([key, sold_data[key]])

result.sort(key=lambda x: (-x[1], x[0]))

print(result[0][0])