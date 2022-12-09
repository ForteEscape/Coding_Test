N = input()

cnt_0 = 0
zero_flg = False
cnt_1 = 0
one_flg = False

for i in range(len(N)):
    if N[i] == '0' and not zero_flg:
        if one_flg:
            one_flg = False
        zero_flg = True
        cnt_0 += 1
    elif N[i] == '1' and not one_flg:
        if zero_flg:
            zero_flg = False
        one_flg = True
        cnt_1 += 1

print(cnt_1 if cnt_1 < cnt_0 else cnt_0)