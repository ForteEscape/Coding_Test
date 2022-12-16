T = int(input())

for _ in range(T):
    N = int(input())

    parent = [0] * (N + 1)
    for _ in range(N - 1):
        v, u = map(int, input().split())
        parent[u] = v

    key_a, key_b = map(int, input().split())
    a_parents, b_parents = [0, key_a], [0, key_b]

    while parent[key_a]:
        a_parents.append(parent[key_a])
        key_a = parent[key_a]

    while parent[key_b]:
        b_parents.append(parent[key_b])
        key_b = parent[key_b]

    idx = 1
    while a_parents[-idx] == b_parents[-idx]:
        idx += 1

    print(a_parents[-idx + 1])