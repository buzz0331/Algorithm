import sys

N, M = map(int, sys.stdin.readline().rstrip().split(" "))
a = []

for i in range(N):
    a.append(sys.stdin.readline().rstrip())

#N과 M중에 더 작은 것 S에 넣기
S = min(N,M)-1
result = 1
check = False

#S ~ 1 까지 반복
for i in range(S,0,-1):
    #결과를 발견했다면 반복문 바로 빠져나옴
    if check:
        break
    #정사각형에서 가장 왼쪽 위에 있는 index 탐색
    for n in range(N):
        if n+i == N:
            break
        for m in range(M):
            if m+i == M:
                break

            c = a[n][m]
            if c == a[n+i][m] and c == a[n][m+i] and c == a[n+i][m+i]:
                result = (i+1)*(i+1)
                check = True

print(result)