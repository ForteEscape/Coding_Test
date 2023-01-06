T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    data = list(map(int, input().split()))
    
    dp = [data[0]]
    
    for i in range(N - 1):
        dp.append(max(dp[i] + data[i + 1], data[i + 1]))
    
    print("#{} {}".format(test_case, max(dp)) )
