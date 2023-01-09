sm = 0
data = []
for _ in range(5):
    N = int(input())
    
    sm += N
    data.append(N)
    
data.sort()
print(sm // 5)
print(data[2])