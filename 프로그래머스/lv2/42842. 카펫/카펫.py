def solution(brown, yellow):
    answer = []
    yellow_size_height = []
    yellow_size_width = []
    
    for i in range(1, int(yellow ** 0.5) + 1):
        if yellow % i == 0:
            yellow_size_height.append(i)
            yellow_size_width.append(yellow // i)
    
    for i in range(len(yellow_size_height)):
        brown_width = yellow_size_width[i] + 2
        brown_height = yellow_size_height[i]
        
        if (brown_width * 2) + (brown_height * 2) == brown:
            answer.append(brown_width)
            answer.append(brown_height + 2)
    
    return answer