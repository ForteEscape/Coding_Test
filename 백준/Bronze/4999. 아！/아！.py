data = list(input())
data2 = list(input())

data1_cnt = 0
for i in range(len(data)):
    if data[i] == 'a':
        data1_cnt += 1

data2_cnt = 0
for i in range(len(data2)):
    if data2[i] == 'a':
        data2_cnt += 1

print("go" if data1_cnt >= data2_cnt else "no")
