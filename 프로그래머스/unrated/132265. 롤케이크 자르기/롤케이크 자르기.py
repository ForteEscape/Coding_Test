from collections import Counter

def solution(topping):
    answer = 0
    dic = Counter(topping)
    dic_set = set()
    
    for element in topping:
        dic[element] -= 1
        dic_set.add(element)
        
        if dic[element] == 0:
            dic.pop(element)
        
        if len(dic) == len(dic_set):
            answer += 1
    
    return answer