board = input()

ans = ''
cnt_x = 0
covered = True

for i in range(len(board)):
    if board[i] == 'X':
        cnt_x += 1
    else:
        polyomino_A = cnt_x // 4
        cnt_x %= 4

        for _ in range(polyomino_A):
            ans += 'AAAA'

        if cnt_x % 2 == 0:
            polyomino_B = cnt_x // 2
            cnt_x %= 2

            for _ in range(polyomino_B):
                ans += 'BB'
        else:
            covered = False
            break

        ans += '.'

polyomino_A = cnt_x // 4
cnt_x %= 4

for _ in range(polyomino_A):
    ans += 'AAAA'

if cnt_x % 2 == 0:
    polyomino_B = cnt_x // 2
    cnt_x %= 2

    for _ in range(polyomino_B):
        ans += 'BB'
else:
    covered = False

if not covered:
    print(-1)
else:
    print(ans)
