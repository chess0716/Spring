import csv
f = open('sokcho_weather_sample.csv', 'r', encoding='utf-8')
reader = csv.reader(f)

header = next(reader)


data = []

for row in reader:
    day = row[0]  
    time = row[2]
    degree = row[3] 
   
    
   
    data.append({'연원일': day, '시분초': time, '온도': degree })

    
for entry in data:
    print(f"{[entry'연원일']}")