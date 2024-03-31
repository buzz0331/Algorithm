import math
import sys

N = int(sys.stdin.readline().rstrip())
count = 0

# for i in range(1,N+1):
#
#     # N이 제곱수인지 확인 -> 제곱수라면 약수의 개수가 홀수이므로 열렸음
#     a = math.sqrt(i)
#     if (a*10) % 10 == 0:
#         count += 1

i = 1

while(True):
    if i*i > N:
        break

    count += 1
    i += 1

print(count)