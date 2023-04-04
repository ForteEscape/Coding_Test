N = int(input())

# table[i] 은 N = i 일 때 문제 조건에 맞게 만들 수 있는 이진 수열의 수
table = [0] * (N + 1)
table[1] = 1

if N >= 2:
    table[2] = 2

    # i >= 3 일 때 table[i]는 table[i - 2]의 순열 에서 00을 추가한 순열
    # 또한 table[i - 1]의 순열에서 1을 추가한 순열이므로 위 두 순열의 합으로 정의될 수 있다.
    for i in range(3, N + 1):
        table[i] = (table[i - 1] + table[i - 2]) % 15746

print(table[N])