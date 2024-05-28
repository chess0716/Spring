import csv 
import re


def opencsv(filename):
    f = open(filename, 'r', encoding='utf-8')
    reader = csv.reader(f)
    output = []
    for i in reader:
        tmp = []
        for j in i:
            if re.search('\d', j):  # 숫자라면
                tmp.append(float(re.sub(',', '', j)))
            else:
                tmp.append(j)
        output.append(tmp)
   
    return output
total = opencsv('popSeoul2.csv')
for i in total[:5]:
    print(i)

for i in total[:5]:
    for j in i :
     try: 
            i[i.index(j)] = float(re.sub(',','',j))
        
     except:
         pass
print(total[:5])