import urllib.request
import bs4

urlNate = 'https://news.nate.com'
htmlObject = urllib.request.urlopen(urlNate)
webPage = htmlObject.read()
bsObject = bs4.BeautifulSoup(webPage , 'html.parser')

tag = bsObject.find('div',{'class','snbArea'})
print(tag)
li_list = tag.findAll('li')
for li in li_list:
    print(li.text, end='')

print()

print ( '## select')
tag2 = bsObject.select_one('#header > div.navWrap > div')
#header > div.navWrap > div
print(tag2)
#header > div.navWrap > div > ul > li.on > h1 > a
li_list = tag2.select('li > a')
li_list = tag2.findAll('li')
for li in li_list:
    print(li.text, end='')

