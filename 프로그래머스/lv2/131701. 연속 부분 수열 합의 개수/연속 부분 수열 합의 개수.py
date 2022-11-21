def solution(elements):
    answer = 0
    result = set()
    
    for i in range(len(elements)):
        elements.append(elements[i])
    
    length = len(elements) // 2
    
    for i in range(1, length + 1):
        for j in range(length):
            res = sum(elements[j:j + i])
            
            if res not in result:
                answer += 1
                result.add(res)
    
    return answer