N = int(input())
data = []

for _ in range(N):
    data.append(float(input()))

for i in range(1, N):
    data[i] = max(data[i - 1] * data[i], data[i])

print("{:.3f}".format(max(data)))