X = int(input())
total = 1
i=2 #몇 번째 줄인지 파악 (피라미드로 생각)
while(X>total):
    total+=i
    i += 1
i-=1
last = total-i #X번째 숫자가 있는 줄의 윗줄의 마지막 숫자의 순서
idx = X-last #X가 현재 줄에서 몇번째에 있는지

##i가 짝수인지 홀수인지 판단
if(i%2 == 0): #짝수일때
        print("%d/%d"%(idx,(i+1-idx)))
else: #홀수일때
        print("%d/%d"%((i+1-idx),idx))






