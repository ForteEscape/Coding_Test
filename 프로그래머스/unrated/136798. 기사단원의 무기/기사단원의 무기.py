def solution(number, limit, power):
    answer = 0
    
    for i in range(1, number + 1):
        data = []
        for j in range(1, int(i ** 0.5) + 1):
            if i % j == 0:
                data.append(j)
                data.append(i // j)
        p = len(set(data))
        if p > limit:
            p = power
        answer += p
            
    
    return answer