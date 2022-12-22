from collections import deque

data = list(input())
stack = deque()
data_len = len(data)

tmp = 1
ans = 0

for i in range(data_len):
    if data[i] == '(':
        tmp *= 2
        stack.append(data[i])
    elif data[i] == '[':
        tmp *= 3
        stack.append(data[i])

    if data[i] == ')':
        if not stack or stack[-1] == '[':
            ans = 0
            break

        if data[i - 1] == '(':
            ans += tmp

        tmp //= 2
        stack.pop()
    elif data[i] == ']':
        if not stack or stack[-1] == '(':
            ans = 0
            break

        if data[i - 1] == '[':
            ans += tmp

        tmp //= 3
        stack.pop()

print(0 if stack else ans)