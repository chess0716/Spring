num,i = 0,0

for i in range(1,11):
    if i % 3 ==0 :
        continue

    num += i

    print ("1~100의 합계 (3의 배수 제외): %d" % num )