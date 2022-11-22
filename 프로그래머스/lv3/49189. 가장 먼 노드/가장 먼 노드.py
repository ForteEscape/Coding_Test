from collections import deque

def solution(n, edge):
    answer = 0
    graph = [[] for _ in range(n + 1)]
    
    for element in edge:
        n1, n2 = element
        
        graph[n1].append(n2)
        graph[n2].append(n1)
    
    visited = [0 for _ in range(n + 1)]
    queue = deque([1])
    visited[1] = True
    
    while queue:
        cur = queue.popleft()
        
        for nv in graph[cur]:
            if not visited[nv]:
                visited[nv] = visited[cur] + 1
                queue.append(nv)
    
    mx_dist = max(visited)
    
    for dist in visited:
        if dist == mx_dist:
            answer += 1
    
    return answer