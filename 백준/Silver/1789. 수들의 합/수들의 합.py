S = int(input())

N = 1

while N <= S:
    S -= N
    N += 1
N -= 1
print(N)