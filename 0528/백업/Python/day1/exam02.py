
money = int(input("교환할 돈은 얼마 :")) #5320

c500 = money // 500 # // 몫
money %= 500 # money = money % 500 %나머지

c100 = money // 100
money %= 100

c50 = money // 50
money %= 50

c10 = money // 10
money %= 10

print("\n 500원짜리 ===> %d" % c500)
print("\n 100원짜리 ===> %d" % c100)
print("\n 50원짜리 ===> %d" % c50)
print("\n 10원짜리 ===> %d" % c10)
print(" 바꾸지 못한 잔돈 ==> %d원 " % money)
