def solution(fees, records):
    answer = []
    cars = {}
    parking_time = {}
    bills = []
    
    for rows in records:
        data = rows.split()
        
        if data[1] not in cars:
            cars[data[1]] = [data[0], data[2]]
        else:
            if data[2] == 'OUT':
                in_time = cars[data[1]][0].split(':')
                out_time = data[0].split(':')
                
                h = int(out_time[0]) - int(in_time[0])
                m = int(out_time[1]) - int(in_time[1])
                
                park_time = h * 60 + m
                
                if data[1] not in parking_time:
                    parking_time[data[1]] = park_time
                else:
                    parking_time[data[1]] += park_time
                
                del cars[data[1]]
                
    for keys in cars:
        in_time = cars[keys][0].split(':')
        h = 23 - int(in_time[0])
        m = 59 - int(in_time[1])
        
        park_time = h * 60 + m
        
        if keys not in parking_time:
            parking_time[keys] = park_time
        else:
            parking_time[keys] += park_time
    
    for keys in parking_time:
        if parking_time[keys] < fees[0]:
            bills.append([keys, fees[1]])
        else:
            res1 = parking_time[keys] - fees[0]
            
            if res1 % fees[2] != 0:
                res2 = res1 // fees[2] + 1
            else:
                res2 = res1 // fees[2]
            
            bills.append([keys, fees[1] + res2 * fees[3]])
    
    bills.sort(key = lambda x: x[0])
    
    for element in bills:
        answer.append(element[1])
    
    return answer