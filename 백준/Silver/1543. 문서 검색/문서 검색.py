data = input()
key = input()

ans = 0
n = 0

while n <= len(data) - len(key):
    if data[n: n + len(key)] == key:
        ans += 1
        n += len(key)
    else:
        n += 1

print(ans)