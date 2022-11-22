def solution(answers):
    answer = []
    data = [
        [1, 2, 3, 4, 5],
        [2, 1, 2, 3, 2, 4, 2, 5],
        [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    ]
    
    score = [[0, 1], [0, 2], [0, 3]]
    
    for p in range(3):
        for i in range(len(answers)):
            if data[p][i % len(data[p])] == answers[i]:
                score[p][0] += 1
    
    score.sort(key= lambda x: x[0], reverse=True)
    mx_score = score[0][0]
    
    for element in score:
        if element[0] == mx_score:
            answer.append(element[1])
            
    return answer