import csv
import time
import datetime

csvName = 'CSV/datetime2.csv'
with open(csvName,'w',newline='') as csvFp:
        csvWriter = csv.writer(csvFp)
        csvWriter.writerow(['연원일','시분초'])

count = 10
while count > 0 :
    count -= 1

    now = datetime.datetime.now()
    yymmdd = now.strftime('%Y-%m-%d')
    hhmmss = now.strftime('%H-%M-%S')
    time_list = [yymmdd,hhmmss]
    print(time_list)

    with open(csvName,'a',newline='') as csvFp:
        csvWriter = csv.writer(csvFp)
        csvWriter.writerow(time_list)
        
    time.sleep(3)
