from collections import deque

test_case = int(input())

for _ in range(test_case):
    deck = deque()

    N = int(input())
    data = list(map(str, input().split()))

    deck.append(data[0])
    for i in range(1, N):
        if data[i] > deck[0]:
            deck.append(data[i])
        else:
            deck.appendleft(data[i])

    print(''.join(deck))

