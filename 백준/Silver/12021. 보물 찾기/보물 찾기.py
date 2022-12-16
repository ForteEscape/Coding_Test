a, b = map(int, input().split())

prev_x, prev_y = a, b
nx, ny = 0.0, 0.0

while True:
    nx = (prev_x + prev_y) / 2
    ny = (2 * (prev_x * prev_y)) / (prev_x + prev_y)

    if prev_x == nx and prev_y == ny:
        break
    else:
        prev_x, prev_y = nx, ny

print(round(prev_x, 4), round(prev_y, 4))