import sys

alphabet = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']

dictionary = dict.fromkeys(alphabet,-1)

S = sys.stdin.readline().rstrip()

for i in range(len(S)):
    if dictionary[S[i]] == -1:
        dictionary[S[i]] = i


# 딕셔너리의 모든 값을 공백을 띄어서 출력
print(" ".join(map(str, dictionary.values())))