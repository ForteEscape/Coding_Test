import math

def get_gcd(array):
    g = array[0]
    
    for i in range(1, len(array)):
        g = math.gcd(g, array[i])
    
    return g

def solution(arrayA, arrayB):
    answer = 0
    
    A, B = get_gcd(arrayA), get_gcd(arrayB)
    
    for element in arrayA:
        if element % B == 0:
            break
    else:
        answer = max(answer, B)
    
    for element in arrayB:
        if element % A == 0:
            break
    else:
        answer = max(answer, A)
    
    return answer
    