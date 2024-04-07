import sys

S = sys.stdin.readline().rstrip().upper()

dict = {}

for i in range(len(S)):
    if S[i] in dict:
        dict[S[i]] += 1
    else:
        dict[S[i]] = 1

M = max(dict.values())

## dict에서 value값이 M인 애들의 key들을 모아서 list로 만듬!!!!!
max_keys = [key for key, value in dict.items() if value == M] ##*********************개중요**************##

##max_keys가 1개만 나왔으면 그 key 출력, 여러개 나왔으면 ? 출력
if len(max_keys) == 1:
    print(max_keys[0])
else:
    print('?')