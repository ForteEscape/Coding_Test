N = int(input())
data = []
table = [[0] * N for _ in range(N)]

direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
location = {}

for i in range(N * N):
    student_data = list(map(int, input().split()))
    data.append(student_data)

for element in data:
    cur_std = element[0]
    cur_std_like = set(element[1:])
    result_data = []

    for i in range(N):
        for j in range(N):
            like_cnt = 0
            zero_cnt = 0

            if table[i][j] != 0:
                continue

            for d in direction:
                nx = j + d[0]
                ny = i + d[1]

                if nx < 0 or nx >= N or ny < 0 or ny >= N:
                    continue

                if table[ny][nx] in cur_std_like:
                    like_cnt += 1
                elif table[ny][nx] == 0:
                    zero_cnt += 1

            result_data.append([like_cnt, zero_cnt, i, j])

    result_data.sort(key=lambda x: (-x[0], -x[1], x[2], x[3]))

    std_y, std_x = result_data[0][2], result_data[0][3]
    location[cur_std] = [std_y, std_x, cur_std_like]
    table[std_y][std_x] = cur_std

ans = 0
for element in data:
    pos_y, pos_x, like_std = location[element[0]]
    cnt = 0

    for d in direction:
        ny = pos_y + d[0]
        nx = pos_x + d[1]

        if ny < 0 or ny >= N or nx < 0 or nx >= N:
            continue

        if table[ny][nx] in like_std:
            cnt += 1

    if cnt >= 1:
        ans += 10 ** (cnt - 1)

print(ans)