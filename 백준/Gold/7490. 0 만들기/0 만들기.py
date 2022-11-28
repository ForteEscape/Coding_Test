from itertools import product
T = int(input())

operator = [' ', '+', '-']

while T > 0:
    T -= 1
    N = int(input())
    data = [x for x in range(1, N + 1)]

    per = product(range(3), repeat=N - 1)

    ans = []
    for element in per:
        formula_data = [data[0]]
        formula = str(data[0])
        idx = 1

        for oper in element:
            if operator[oper] == ' ':
                formula_data[-1] = int(str(formula_data[-1]) + str(data[idx]))
            else:
                formula_data.append(operator[oper])
                formula_data.append(data[idx])

            formula += operator[oper]
            formula += str(data[idx])
            idx += 1

        res = formula_data[0]
        for i in range(1, len(formula_data), 2):
            if formula_data[i] == '-':
                res -= formula_data[i + 1]
            elif formula_data[i] == '+':
                res += formula_data[i + 1]

        if res == 0:
            ans.append(formula)

    for element in ans:
        print(element)
    print('')