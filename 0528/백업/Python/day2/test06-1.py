import csv
import re

f = open('popSeoul.csv', 'r', encoding='utf-8')
reader = csv.reader(f)

# 숫자, 천단위를 제거하고 문자를 숫자 float로 변환
output = []
for i in reader:
    tmp = []
    for j in i:
        if re.search('\d', j):  # 숫자라면
            tmp.append(float(re.sub(',', '', j)))
        else:
            tmp.append(j)
    output.append(tmp)

result = [['구', '한국인', '외국인', '외국인 비율%']]

for i in output:
    try:
        foreign_ratio = round(i[2] / (i[1] + i[2]) * 100, 2)
        if foreign_ratio >= 5:
            result.append([i[0], i[1], i[2], foreign_ratio])
    except :
        pass
    
print(result)
####################
test = [1,2,3,4,5]
print(test.index(5))
j = '1,468,246'
print(float(re.sub(',','',j)))
print(int(re.sub(',','',j)))



    


