N = int(input())
data = list(map(int, input().split()))
ans = 0


def merge_sort(data, left, right):
    global ans

    if left == right:
        return

    mid = (left + right) // 2
    merge_sort(data, left, mid)
    merge_sort(data, mid + 1, right)

    temp_data = []

    lp, rp = left, mid + 1

    while lp <= mid and rp <= right:
        if data[lp] <= data[rp]:
            temp_data.append(data[lp])
            lp += 1
        else:
            temp_data.append(data[rp])
            # lp ~ mid 가 더 클 경우 남은 lp ~ mid 까지의 배열 길이를 스왑한 값으로 한다.
            ans += mid - lp + 1
            rp += 1

    while lp <= mid:
        temp_data.append(data[lp])
        lp += 1

    while rp <= right:
        temp_data.append(data[rp])
        ans += mid - lp + 1
        rp += 1

    for i in range(len(temp_data)):
        data[left + i] = temp_data[i]

    del temp_data


merge_sort(data, 0, len(data) - 1)
print(ans)
