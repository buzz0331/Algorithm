alphabet_list = ["c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="]

S = input()
count = 0
i=0

#i는 입력값을 순차적으로 읽어주는 iterator
while i < len(S):
    #현재 비교하려는 char

    search_cycle = 0
    old_i = i
    #j는 alphabet_ist의 index
    for a in alphabet_list:

        # alphabet_list에 있는 단어와 입력값을 완전히 비교시작
        for idx in range(len(a)):
            #i+idx가 입력값의 길이를 넘어갈때 예외처리
            if i+idx == len(S):
                break
            #불일치하는 경우 발생!
            if S[i+idx] != a[idx]:
                break
            #완전히 일치하는 경우
            if idx == len(a)-1:
                count += 1
                i += idx
                break
    #일치하는 알파벳이 없는 경우
    if(old_i == i):
        count += 1
    i+=1

print(count)


