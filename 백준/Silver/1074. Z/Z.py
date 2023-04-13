N, r, c = map(int, input().split())


def visited(N, r, c):
    if N == 0:
        return 0

    half = 2 ** (N-1)
    if r < half and c < half:
        return visited(N - 1, r, c)
    elif r < half and c >= half:
        return half*half + visited(N - 1, r, c - half)
    elif r >= half and c < half:
        return 2 * half * half + visited(N - 1, r - half, c)
    return 3 * half * half + visited(N - 1, r - half, c - half)


print(visited(N, r, c))
