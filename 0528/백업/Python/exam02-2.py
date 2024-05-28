money = 0
money = int(input("교환할 돈은 얼마 :")) #5320
don = [500,100,50,10]

for i in don:   
        print(" %d 원짜리 ===> %d" % (i ,int (money//i)))
        money %= i

print("바꾸지못한돈 ===> %d원" % money)





