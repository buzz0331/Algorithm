import sys

sum = 0
prev_sum = 0
for i in range(10):
    score = int(sys.stdin.readline().rstrip())

    prev_sum = sum
    A = 100 - prev_sum

    sum += score
    B = 100 - sum

    if abs(B) > abs(A):
        print(prev_sum)
        break

    if i == 9:
        print(sum)


