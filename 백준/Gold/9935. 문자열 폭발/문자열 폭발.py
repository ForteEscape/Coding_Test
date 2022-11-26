from collections import deque

data = input()
bomb = input()

stack = []
bomb_len = len(bomb)

for i in range(len(data)):
    stack.append(data[i])

    if ''.join(stack[-bomb_len:]) == bomb:
        for _ in range(bomb_len):
            stack.pop()

print(''.join(stack)) if stack else print("FRULA")