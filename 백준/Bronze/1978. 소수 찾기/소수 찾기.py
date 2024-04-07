import sys

N = int(sys.stdin.readline().rstrip())
S = [*map(int, sys.stdin.readline().rstrip().split(" "))]
count = 0

for i in range(len(S)):
    index = S[i]
    if index == 1:
        continue
    elif index == 2:
        count += 1
        continue
    else:
        for j in range(2,index):

            if S[i]%j == 0:
                break
            if j == index-1:
                count += 1

print(count)