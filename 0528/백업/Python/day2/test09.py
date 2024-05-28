import csv
# 첫번째 행에 컴퓨터,노트북,태블릿
# 두번째 행에 100,80,60
# 리스트 형태로 표현
test_list = [['컴퓨터','노트북','태블릿'],['100','80','60']]

with open ('test.csv','w',newline='',encoding='utf-8') as test:
    writer = csv.writer(test)
    writer.writerows(test_list)

print('save')