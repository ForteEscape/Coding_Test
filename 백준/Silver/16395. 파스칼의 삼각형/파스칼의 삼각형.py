N, K = map(int, input().split())
board = [[0 for _ in range(N + 2)] for _ in range(N + 1)]

for i in range(1, N + 1):
    board[i][1] = 1

for i in range(2, N + 1):
    for j in range(1, i + 1):
        board[i][j] = board[i - 1][j] + board[i - 1][j - 1]

print(board[N][K])