N = int(input())

A = N
count = 0
while True:
    old_A = A
    if A < 10:
        A = A*10

    #각 자리의 숫자를 더한다 -> 가장 오른쪽 자리 수
    x = ((A // 10) + (A % 10)) % 10
    #주어진 수의 가장 오른쪽 자리의 수
    y = old_A % 10
    A = y * 10 + x

    count += 1
    if N == A:
        break

print(count)