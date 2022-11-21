def solution(k, m, score):
    answer = 0
    
    score.sort()
    score = score[len(score) % m:]
    
    for i in range(0, len(score), m):
        answer += min(score[i:i + m]) * m
    
    return answer