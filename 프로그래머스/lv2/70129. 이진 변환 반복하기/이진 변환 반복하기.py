def solution(s):
    answer = []
    zero_count = 0
    loof_cnt = 0
    
    while s != '1':
        loof_cnt += 1
        transform_data = ''
        
        for i in range(len(s)):
            if s[i] == '1':
                transform_data += s[i]
            else:
                zero_count += 1
        
        s = bin(len(transform_data))[2:]
    
    answer.append(loof_cnt)
    answer.append(zero_count)
    return answer