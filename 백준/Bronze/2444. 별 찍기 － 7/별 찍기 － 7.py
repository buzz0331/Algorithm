N = int(input())
total = 2*N-1 #한 줄에 찍혀야 하는 token 개수
for i in range(1,total+1):
    
    if i <= N:
        num = 2*i-1 #*이 찍혀야 되는 개수
    else:
        num = 2*(2*N-i)-1 #*이 찍혀야 되는 개수

    if i == total:
        print(" " * ((total - num) // 2) + ("*" * num), end='')
    else:
        print(" " * ((total - num) // 2) + ("*" * num))