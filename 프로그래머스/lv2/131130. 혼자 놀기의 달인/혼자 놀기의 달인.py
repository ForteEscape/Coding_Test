def solution(cards):
    answer = 0
    group = []
    visited = [False for _ in range(len(cards))]
    
    for i in range(len(cards)):
        if not visited[i]:
            visited[i] = True
            idx = cards[i] - 1
            tmp_data = 1
            
            while not visited[idx]:
                visited[idx] = True
                idx = cards[idx] - 1
                tmp_data += 1
            
            group.append(tmp_data)
    
    if len(group) <= 1:
        return 0
    else:
        group.sort(reverse=True)
        return group[0] * group[1]
        