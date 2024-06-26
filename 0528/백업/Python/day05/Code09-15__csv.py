import bs4
import urllib.request
import csv
def getBookInfo(book_tag) :
    names = book_tag.find("div",{"class":"goods_name"})
    bookName = names.find('a').text
    auths = book_tag.find("span",{"class":"goods_auth"})
    bookAuth = auths.find('a').text
    bookPub = book_tag.find("span",{"class":"goods_pub"}).text
    bookDate = book_tag.find("span",{"class":"goods_date"}).text
    bookPriceTag = book_tag.find("em", {"class": "yes_b"})
    bookPrice = bookPriceTag.text if bookPriceTag else 'N/A'
    return [bookName,bookAuth,bookPub,bookDate,bookPrice]










url = "https://www.yes24.com/24/Category/Display/001001046001?ParamSortTp=05&PageNumber="
PageNumber = 1
csvName = 'Books.csv'
with open(csvName,'w',newline='') as csvFp:
    csvWriter = csv.writer(csvFp)
    csvWriter.writerow(['책이름','저자','출판사','출간일','가격'])

while True:
    try:
        bookUrl = url + str(PageNumber)
        PageNumber += 1
        htmlObject = urllib.request.urlopen(bookUrl)
        webPage = htmlObject.read()
        bsObject = bs4.BeautifulSoup(webPage,'html.parser')
        tag = bsObject.find('ul',{'class':'clearfix'})
        all_books = tag.findAll('div',{'class':'goods_info'})
        
        for book in all_books:
             info_list = getBookInfo(book)
             with open(csvName,'a',newline='') as csvFp:
                 csvWriter = csv.writer(csvFp)
                 csvWriter.writerow(info_list)
    
    
    except:
        break
print('save')