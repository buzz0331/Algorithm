import sys

N = int(sys.stdin.readline().rstrip())

list = []
# tempo_list = []

for i in range(N):
    x, y = map(int, sys.stdin.readline().rstrip().split(" "))
    t = (x, y)

    list.append(t)
list.sort()
    # if len(list) == 0:
    #     list.append(t)
    # else:
    #     #list에 남아있는 tuple이 없을 때까지
    #     # list에 있는 tuple을 모두 pop해서 임시list에 넣어줌
    #     while len(list) != 0:
    #         tempo_list.append(list.pop())
    #
    #     while len(tempo_list) != 0:
    #         tup = tempo_list.pop()
    #
    #         if x < tup[0]:
    #             list.append(t)
    #             list.append(tup)
    #             while len(tempo_list) != 0:
    #                 list.append(tempo_list.pop())
    #
    #         elif x == tup[0]:
    #             if y < tup[1]:
    #                 list.append(t)
    #                 list.append(tup)
    #                 while len(tempo_list) != 0:
    #                     list.append(tempo_list.pop())
    #             else:
    #                 list.append(tup)
    #                 if len(tempo_list) == 0:
    #                     list.append(t)
    #         else:
    #             list.append(tup)
    #             if len(tempo_list) == 0:
    #                 list.append(t)

for table in list:
    print(table[0], table[1])