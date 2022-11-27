N = int(input())

data = [list(map(int, input().split())) for _ in range(N)]
data.sort(key=lambda x: x[1], reverse=True)

start_time = data[0][1]

for i in range(N):
    spend_day = data[i][0]
    dead_line = data[i][1]

    if start_time >= dead_line:
        start_time = dead_line - spend_day
    else:
        start_time -= spend_day

print(start_time)
