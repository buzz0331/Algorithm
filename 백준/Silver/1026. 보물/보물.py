#두가지 방법이 있음
#1. A의 작은수부터 B의 큰수끼리 곱해서 더함
#2. S를 구하고 이후에 S끼리 비교해서 최솟값 찾기 // A를 미는 방식으로 하면 X 순서를 뒤바꾸기도 해야함

N = int(input())
A = [*map(int,input().split(" "))] #input으로 받은 것을 쪼개서 int형으로 모두 바꿔서 list로 반환
B = [*map(int,input().split(" "))]

S=0
A.sort()
B.sort()
B.reverse()

for i in range(N):
    S += A[i]*B[i]

print(S)



