import sys

N = int(input())

for _ in range(N):
    case = sys.stdin.readline().rstrip()
    count = 0
    sequence = 0
    o = False
    for c in case:
        #이전 case가 x였다면
        if not o:
            sequence = 0
        #현재 case가 o라면
        if c == 'O':
            sequence += 1
            count += sequence
            o = True
        else:
            # count += sequence
            o = False


    print(count)