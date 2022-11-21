from collections import deque

def solution(food):
    answer = ''
    front_deck = deque()
    back_deck = deque()
    
    for i in range(1, len(food)):
        cnt = food[i] // 2
        
        for _ in range(cnt):
            front_deck.append(i)
            back_deck.append(i)
    
    front = ''
    back = ''
    while front_deck:
        front += str(front_deck.popleft())
        back += str(back_deck.pop())
    
    answer = front + '0' + back
    
    return answer