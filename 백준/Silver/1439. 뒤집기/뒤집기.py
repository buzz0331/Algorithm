S = input()

idx = 0
#1 -> 0#
count_a = 0
while idx < len(S):
    if S[idx] == '1':
        count_a += 1
        for j in range(idx+1,len(S)):
            if S[j] == '1':
                idx += 1
            else:
                break

    idx += 1

idx = 0
#0 -> 1#
count_b = 0
while idx < len(S):
    if S[idx] == '0':
        count_b += 1
        for j in range(idx+1,len(S)):
            if S[j] == '0':
                idx += 1
            else:
                break

    idx += 1

if count_a >= count_b:
    print(count_b)
else:
    print(count_a)