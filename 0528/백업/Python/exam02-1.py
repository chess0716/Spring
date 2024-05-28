money = 0
money = int(input("교환할 돈은 얼마 :")) #5320



print("\n 500원짜리 ===> %d" % int (money//500))
money %= 500
print("\n 100원짜리 ===> %d" % int (money//100))
money %= 100
print("\n 50원짜리 ===> %d" % int (money//50))
money %= 50
print("\n 10원짜리 ===> %d" % int (money//10))
money %= 10
print(" 바꾸지 못한 잔돈 ==> %d원 " % money)