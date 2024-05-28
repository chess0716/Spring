from bs4 import BeautifulSoup
import urllib.request
import pandas as pd

def getBookInfo(book_tag):
  
    authorName = book_tag.select_one('div.b-text div.b-author').text
    
    price = book_tag.select_one('div.b-text div.b-price strong').text

    bookName = book_tag.select_one('div.b-text h4 a').text

   
    
    return [bookName, authorName, price]

bookUrl = 'https://www.aladin.co.kr/shop/wbrowse.aspx?CID=351'
htmlObject = urllib.request.urlopen(bookUrl)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

tag_list = bsObject.select('ul.b-booklist > li')
bookdata = []

for tag in tag_list:
    bookdata.append(getBookInfo(tag))

df = pd.DataFrame(bookdata, columns=['책이름', '저자', '가격'])
df.to_csv('aladin_pandas.csv', index=False, encoding='utf-8-sig')


df = pd.read_csv('aladin_pandas.csv')
print(df)








