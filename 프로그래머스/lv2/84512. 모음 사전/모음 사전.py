word_list = ['A', 'E', 'I', 'O', 'U']
cnt = 0
is_found = False

def solve(text, ans, cur_length):
    global cnt
    global is_found
    
    if text == ans:
        is_found = True
        return
    
    if cur_length == 5:
        return
    
    for i in range(len(word_list)):
        text += word_list[i]
        cnt += 1
        
        solve(text, ans, cur_length + 1)
        
        text = text[:-1]
        
        if is_found:
            return

def solution(word):
    global cnt
    solve('', word, 0)
    
    return cnt