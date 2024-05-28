import usecsv
import csv

total = usecsv.opencsv('popSeoul.csv')
nlist = usecsv.switchcsv(total)
print(nlist[:5])

result =  [['구', '한국인', '외국인', '외국인 비율%']]
for i in nlist:
    foreign = 0
    try:
        foreign = round(i[2]/(i[1]+i[2])*100,2)
        if foreign > 7 :
            result.append(i[1],i[2],i[3],foreign)
    except:
        pass
print(result)

row_str = ','.join(map(str,result))

with open('foreign.csv', 'w', newline='', encoding='utf-8') as outFp:
    csvWriter = csv.writer(outFp)
    csvWriter.writerows(result)
       

print("save")