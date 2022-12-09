directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]
well_ruled = True

def dfs(y, x, pivot_y, pivot_x, visited, board):
    global well_ruled
    visited[y][x] = True
    
    for d in directions:
        ny = y + d[0]
        nx = x + d[1]
        
        if ny < 0 or ny >= 5 or nx < 0 or nx >= 5:
            continue
        
        if is_in_distance(ny, nx, pivot_y, pivot_x):
            if board[ny][nx] != 'X' and not visited[ny][nx]:
                dfs(ny, nx, pivot_y, pivot_x, visited, board)
                
                if board[ny][nx] == 'P':
                    well_ruled = False
            
            
def is_in_distance(y, x, pivot_y, pivot_x):
    distance = abs(pivot_y - y) + abs(pivot_x - x)
    
    return True if distance <= 2 else False


def solution(places):
    global well_ruled
    
    answer = []
    for i in range(5):
        location = []
        visited = [[False for _ in range(5)] for _ in range(5)]
        well_ruled = True
        
        tmp = places[i]
        board = []
        for element in tmp:
            board.append(list(element))
        
        for j in range(5):
            for k in range(5):
                if board[j][k] == 'P':
                    location.append([j, k])
        
        for j in range(len(location)):
            y, x = location[j][0], location[j][1]
            pivot_y, pivot_x = location[j][0], location[j][1]
            
            dfs(y, x, pivot_y, pivot_x, visited, board)
            
        if well_ruled:
            answer.append(1)
        else:
            answer.append(0)
    
    return answer