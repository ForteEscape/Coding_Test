node_cnt = 1

def dfs(graph, v, visited):
    global node_cnt
    
    for nv in graph[v]:
        if not visited[nv]:
            node_cnt += 1
            visited[nv] = True
            dfs(graph, nv, visited)

def solution(n, wires):
    global node_cnt
    answer = 1e9
    
    for i in range(n - 1):
        graph = [[] for _ in range(n + 1)]
        visited = [False for _ in range(n + 1)]
        nodes = []
        
        for w in range(len(wires)):
            if w == i:
                continue
            
            v1, v2 = wires[w]
            graph[v1].append(v2)
            graph[v2].append(v1)
        
        for j in range(1, n + 1):
            if not visited[j]:
                visited[j] = True
                node_cnt = 1
                dfs(graph, j, visited)
                nodes.append(node_cnt)
                
        diff = abs(nodes[0] - nodes[1])
        answer = min(answer, diff)
            
    return answer