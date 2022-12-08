fruit, s_len = map(int, input().split())
height = list(map(int, input().split()))

height.sort()

for element in height:
    if element <= s_len:
        s_len += 1
    else:
        break

print(s_len)