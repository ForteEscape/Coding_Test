from collections import deque

def solution(queue1, queue2):
    answer = -2
    
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    sm1 = sum(queue1)
    sm2 = sum(queue2)
    
    for i in range(len(queue1) * 4):
        if sm1 == sm2:
            answer = i
            break
        
        if sm1 < sm2:
            data = queue2.popleft()
            queue1.append(data)
            sm2 -= data
            sm1 += data
        else:
            data = queue1.popleft()
            queue2.append(data)
            sm1 -= data
            sm2 += data
    else:
        answer = -1
    
    
    return answer