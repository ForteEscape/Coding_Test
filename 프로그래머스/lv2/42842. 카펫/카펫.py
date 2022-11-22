def solution(brown, yellow):
    answer = []
    yellow_size_height = []
    yellow_size_width = []
    
    for i in range(1, int(yellow ** 0.5) + 1):
        if yellow % i == 0:
            yellow_size_height.append(i)
            yellow_size_width.append(yellow // i)
    
    for i in range(len(yellow_size_height)):
        if (yellow_size_width[i] + 2) * 2 + yellow_size_height[i] * 2 == brown:
            answer.append(yellow_size_width[i] + 2)
            answer.append(yellow_size_height[i] + 2)
    
    return answer