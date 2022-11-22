from itertools import permutations

prime_list = set()

def is_prime(data):
    if data <= 1:
        return False
    
    for i in range(2, int(data ** 0.5) + 1):
        if data % i == 0:
            return False
    
    if data in prime_list:
        return False
    else:
        prime_list.add(data)
        return True

def solution(numbers):
    answer = 0
    numbers = list(numbers)
    
    for i in range(1, len(numbers) + 1):
        per = list(permutations(numbers, i))
        
        for element in per:
            prime_test = ''
            for data in element:
                prime_test += data
            
            if is_prime(int(prime_test)):
                answer += 1
    
    return answer