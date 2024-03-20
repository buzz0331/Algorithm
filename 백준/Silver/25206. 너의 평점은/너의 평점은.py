import sys

score = dict()

score["A+"] = 4.5
score["A0"] = 4.0
score["B+"] = 3.5
score["B0"] = 3.0
score["C+"] = 2.5
score["C0"] = 2.0
score["D+"] = 1.5
score["D0"] = 1.0
score["F"] = 0.0
sum = 0.0
sum_credit = 0.0

for i in range(20):
    name, credit, grade = sys.stdin.readline().rstrip().split(" ")
    credit = float(credit)

    if grade != "P":
        sum += credit * score[grade]
        sum_credit += credit




result = sum / sum_credit
print("{:.6f}".format(result))

