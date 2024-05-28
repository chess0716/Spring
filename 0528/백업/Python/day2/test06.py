import csv


f = open('popSeoul.csv', 'r', encoding='utf-8')
reader = csv.reader(f)


header = next(reader)


data = []

for row in reader:
    gu = row[0]  
    korean_population = int(row[1].replace(',', ''))  
    foreigner_population = int(row[2].replace(',', ''))  
    Senior_population = int (row[3].replace(',',''))
    
    # 구 이름과 각각의 인구 수 저장
    data.append({'구': gu, '한국인': korean_population, '외국인': foreigner_population,'노인':Senior_population })




for entry in data:
    ratio = (entry['외국인'] / (entry['한국인'] + entry['외국인'])) * 100
    print(f"{entry['구']} - 한국인: {entry['한국인']}명, 외국인: {entry['외국인']}명, 외국인 비율: {ratio:.2f}%")

for entry2 in data:
    ratio = (entry2['노인'] / (entry2['한국인'] + entry2['노인'])) * 100
    print(f"{entry2['구']} - 한국인: {entry2['한국인']}명, 외국인: {entry2['외국인']}명, 외국인 비율: {ratio:.2f}%  노인: {entry2['노인']}명 , 노인 비율: {ratio:.2f}%")

