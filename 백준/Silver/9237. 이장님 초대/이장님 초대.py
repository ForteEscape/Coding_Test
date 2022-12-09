N = int(input())
tree_data = list(map(int, input().split()))
tree_data.sort(reverse=True)

grow_date = []

weight = N - 1
for i in range(N):
    grow_date.append(tree_data[i] - weight)
    weight -= 1

print(max(grow_date) + N + 1)