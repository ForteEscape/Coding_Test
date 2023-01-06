N, K = map(int, input().split())
W = [0]
V = [0]

# column 이 배낭의 무게 k, row가 물품의 수 N인 K * N dp table을 만든다.
dp = [[0] * (K + 1) for _ in range(N + 1)]

for i in range(N):
    w, v = map(int, input().split())
    W.append(w)
    V.append(v)

for i in range(1, N + 1):
    for j in range(1, K + 1):
        if W[i] > j:
            dp[i][j] = dp[i - 1][j]
        else:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i])

print(dp[N][K])