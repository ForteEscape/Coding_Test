from collections import deque

N, P = map(int, input().split())
stack_list = []

for _ in range(N + 1):
    stack_list.append(deque())

ans = 0
for _ in range(N):
    line, plat = map(int, input().split())

    if not stack_list[line]:
        stack_list[line].append(plat)
        ans += 1
        continue

    if stack_list[line][-1] >= plat:
        while True:
            if not stack_list[line] or stack_list[line][-1] <= plat:
                if not stack_list[line]:
                    stack_list[line].append(plat)
                    ans += 1
                elif stack_list[line][-1] < plat:
                    stack_list[line].append(plat)
                    ans += 1
                break

            stack_list[line].pop()
            ans += 1
    else:
        stack_list[line].append(plat)
        ans += 1

print(ans)
