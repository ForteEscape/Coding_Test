def solution(k, d):
    answer = 0
    
    for i in range(0, d + 1, k):
        max_y = get_max_y(i, d)
        answer += (max_y // k) + 1
    
    return answer

# x^2 + y^2 = d^2
# y^2 = d^2 - x^2
def get_max_y(x, d):
    x_len = x ** 2
    d_len = d ** 2
    
    return (d_len - x_len) ** 0.5