n = int(input())

is_used_column = [False for _ in range(n)]
is_used_diagonal1 = [False for _ in range(n * 2)]  # 좌상단 대각선
is_used_diagonal2 = [False for _ in range(n * 2)]  # 우하단 대각선
ans = 0


# 퀸 배치수, 현재 좌표쌍
def solve(queen):
    if queen == n:
        global ans
        ans += 1
        return

    for i in range(n):
        if is_used_column[i] or is_used_diagonal1[i + queen] or is_used_diagonal2[i - queen + n - 1]:
            continue

        is_used_column[i] = True
        is_used_diagonal1[queen + i] = True
        is_used_diagonal2[i - queen + n - 1] = True
        solve(queen + 1)
        is_used_column[i] = False
        is_used_diagonal1[queen + i] = False
        is_used_diagonal2[i - queen + n - 1] = False


solve(0)
print(ans)