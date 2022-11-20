N, M = map(int, input().split())
data = list(input())

stack = []
for i in data:
    while stack and M > 0 and stack[-1] < i:
        stack.pop()
        M -= 1
    stack.append(i)

if M > 0:
    print(''.join(stack[:-M]))
else:
    print(''.join(stack))
