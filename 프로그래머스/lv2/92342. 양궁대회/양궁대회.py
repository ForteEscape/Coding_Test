from itertools import combinations_with_replacement

def solution(n, info):
    answer = []
    mx_gap = -1
    
    for combi in combinations_with_replacement(range(11), n):
        score = [0]*11
        
        for i in combi:
            score[10 - i] += 1
        
        score_apeach, score_lion = 0, 0
        
        for i in range(11):
            if info[i] == score[i] == 0:
                continue
            
            if info[i] >= score[i]:
                score_apeach += 10 - i
            else:
                score_lion += 10 - i
        
        if score_lion > score_apeach:
            if mx_gap < score_lion - score_apeach:
                mx_gap = score_lion - score_apeach
                answer = score
    
    if not answer:
        answer.append(-1)
    
    return answer