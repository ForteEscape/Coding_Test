from collections import Counter

def solution(want, number, discount):
    answer = 0
    
    for i in range(len(discount) - 10 + 1):
        is_exists = True
        is_available = True
        discount_data = Counter(discount[i:i + 10])
        
        for j in range(len(want)):
            if want[j] not in discount_data:
                is_exists = False
                break
        
        if not is_exists:
            continue
        
        for j in range(len(want)):
            if discount_data[want[j]] != number[j]:
                is_available = False
                break
        else:
            answer += 1
    
    return answer