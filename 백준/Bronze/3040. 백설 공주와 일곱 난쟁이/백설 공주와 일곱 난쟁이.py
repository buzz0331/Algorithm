import sys

people = []
correct = False

for _ in range(9):
    people.append(int(sys.stdin.readline().rstrip()))

sub = sum(people) - 100

for i in range(8):
    for j in range(i+1,9):
        if people[i] + people[j] == sub:
            people.pop(i)
            people.pop(j-1)
            correct = True
            break

    if correct:
        break

for p in people:
    print(p)