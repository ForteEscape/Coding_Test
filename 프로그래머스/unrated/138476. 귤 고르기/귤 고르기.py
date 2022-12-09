from collections import Counter

def solution(k, tangerine):
    answer = 0
    
    data = Counter(tangerine)
    data = sorted(data.items(), key=lambda x: x[1], reverse=True)
    
    box = 0
    for element in data:
        box += element[1]
        answer += 1
        
        if box >= k:
            break
    
    return answer