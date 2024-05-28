from bs4 import BeautifulSoup
import urllib.request
import re

url = "https://finance.naver.com/marketindex/"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

tag = bsObject.select_one('#exchangeList > li.on > a.head.usd > div ')

print(tag.select('span')[0].string)

print('-----------------------')
tag2 = tag.select('span')
print ( tag2 )
for sp in tag2:
     print(sp.string)

from bs4 import BeautifulSoup
import urllib.request
import re

url = "https://finance.naver.com/marketindex/"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

tag = bsObject.select_one('#exchangeList > li.on > a.head.usd > div ')

print(tag.select('span')[0].string)

print('-----------------------')
tag2 = tag.select('span')
print ( tag2 )
for sp in tag2:
     print(sp.string)
price = bsObject.select_one('#exchangeList > li.on > a.head.usd > div > span.value').string
print ('price : ',price)
updown = bsObject.select_one('#exchangeList > li.on > a.head.usd > div > span.blind').string

print('updown :',updown)


        