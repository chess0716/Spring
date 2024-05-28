import csv
with open('singerA.csv','r') as inFpA:
    with open('singerB.csv','r') as inFpB:
      with open('singerSumout.csv','w',newline='') as outFp:
        csvReaderA = csv.reader(inFpA)
        csvReaderB = csv.reader(inFpB)
        csvWriter = csv.writer(outFp)
        header_list = next(csvReaderA)
        header_list = next(csvReaderB)
        csvWriter.writerow(header_list)

        for row_list in csvReaderA:
           csvWriter.writerow(row_list)
        for row_list in csvReaderB:
           csvWriter.writerow(row_list)

print('save ok')