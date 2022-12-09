from itertools import combinations

N = int(input())
board = [list(map(str, input().split())) for _ in range(N)]

obj_location = []
teacher_location = []
for i in range(N):
    for j in range(N):
        if board[i][j] == 'X':
            obj_location.append([i, j])

        if board[i][j] == 'T':
            teacher_location.append([i, j])

arrangement = combinations(range(len(obj_location)), 3)
direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]

for element in arrangement:
    can_fount = False
    loc1, loc2, loc3 = element

    board[obj_location[loc1][0]][obj_location[loc1][1]] = 'O'
    board[obj_location[loc2][0]][obj_location[loc2][1]] = 'O'
    board[obj_location[loc3][0]][obj_location[loc3][1]] = 'O'

    for t_location in teacher_location:
        for i in range(4):
            t_y, t_x = t_location
            while True:
                t_y += direction[i][0]
                t_x += direction[i][1]

                if t_y < 0 or t_y >= N or t_x < 0 or t_x >= N:
                    break

                if board[t_y][t_x] == 'S':
                    can_fount = True
                    break

                if board[t_y][t_x] == 'O':
                    break

            if can_fount:
                break
        if can_fount:
            break

    if not can_fount:
        print("YES")
        break

    board[obj_location[loc1][0]][obj_location[loc1][1]] = 'X'
    board[obj_location[loc2][0]][obj_location[loc2][1]] = 'X'
    board[obj_location[loc3][0]][obj_location[loc3][1]] = 'X'

else:
    print("NO")
