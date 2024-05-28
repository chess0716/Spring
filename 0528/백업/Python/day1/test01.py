list2 = []
list1 = []
value = 1
for i in range(0, 3):
    list1 = []  # 각 행을 독립된 빈 리스트로 초기화
    for k in range(0, 4):
        list1.append(value)
        value += 1
    list2.append(list1)

for i in range(0, 3):
    for k in range(0, 4):
        print("%1d" % list2[i][k], end=" ")
    print("")  # 각 행을 출력한 후 줄 바꿈

    