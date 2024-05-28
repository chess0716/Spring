# # # 조건문
# # a = 2 
# # if (a==1):
# #     print(1)
# # else :
# #     print('1 아님')
    
# # if (a==1) :
# #     print(1)
# # elif a==2 :
# #     print(2)
# # else :
# #     print(3)

# # # 반복문
# # for  i in [1,2,3] :
# #     print(i)


# # for i in "Hello" :
# #     print(i)


# # num = 5
# # while num > 0:
# #     print (num)
# #     num -= 1

# # # num 이 6이면 end 를 출력하고 종료

# num = 10
# while num > 0 :
#     num -= 1
#     if num==6 :
#        print("end")
#     else :
#         print (num)

# nums = [1,2,3,4,5]
# test = [3,6,7]
# for i in nums :
#     if i in test : ## 참 , 거짓
#         print(i)

# 100 까지 의 수중 7 의 배수와 합계 출력


# total = 0
 
 
# for i in range(1,101) :
#      if i % 7 == 0 :
#         print("7의 배수 :",i)
#         total += i
        
#         print ("합계",total)
# list1 = []
# list2 = []
# for i in range(0,3):
#     list1 = []
#     for j in range (0,3):
#         list1.append("*")
#         list2.append(list1)


#         print("%s" % list2[i][j], end=" ")
#     print("")  

d1 = {
    'a': 1,
    'b': 2,
    'c': 3
}
max_value = max(d1.values())

print("최댓값:", max_value)
print( d1.items())

# 최대 value의 키값 출력
for k, v in d1.items():
    if v == max_value:
        print('최대 value의 키값:', k)





















        