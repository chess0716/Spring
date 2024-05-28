import csv
f = open('pythonbook.csv','r')
reader = csv.reader(f)



header = next(reader)

data = []

for row in reader:
    bookName = row[0]
    author = row[1]
    bookStore = row[2]
    bookDay = row[3]
    price = int(row[4].replace(',',''))


    data.append({'책이름':bookName,'저자':author,'출판사':bookStore , '출간일':bookDay,'가격':price})



for entry in data:
         print(f"{entry['책이름']}, {entry['저자']}, {entry['출판사']}, {entry['출간일']}, {entry['가격']}")
