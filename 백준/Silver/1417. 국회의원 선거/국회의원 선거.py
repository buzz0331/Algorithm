N = int(input())

count = 0 #얼마나 매수하는지
candidate = []
me = int(input())
for i in range(N-1): #자신을 제외한 나머지 후보들 배열에 저장
    candidate.append(int(input()))

if N==1: #후보가 나혼자라면
    max_c = 0
else:
    max_c = max(candidate)

if me > max_c:
    print(count)
elif me == max_c:
    print(count+1)
else:
    while me <= max_c:
        max_idx = candidate.index(max(candidate))
        candidate[max_idx] -= 1
        me += 1
        count+=1
        max_c = max(candidate)
    print(count)