from collections import deque

def solution(s):
    answer = 0
    queue = deque(s)
    
    round_count = len(s)
    for i in range(round_count):
        stack = deque()
        for j in range(round_count):
            if s[j] in ['[', '(', '{']:
                stack.append(s[j])
            
            if s[j] in [']', ')', '}']:
                if stack:
                    if stack[-1] == '[' and s[j] == ']':
                        stack.pop()
                    elif stack[-1] == '(' and s[j] == ')':
                        stack.pop()
                    elif stack[-1] == '{' and s[j] == '}':
                        stack.pop()
                    else:
                        stack.append(s[j])
                else:
                     stack.append(s[j])   
        if not stack:
            answer += 1
        
        queue.append(queue.popleft())
        s = ''.join(queue)
    
    return answer