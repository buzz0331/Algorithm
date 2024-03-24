import sys

N, C = map(int, sys.stdin.readline().rstrip().split(" "))

time = [0]*(C+1)
for i in range(N):
    a = int(sys.stdin.readline().rstrip())
    for j in range(a,C+1,a):
        if time[j] == 0:
            time[j] = 1


print(sum(time))