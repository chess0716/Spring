import bs4

webPage = open('HTML/Sample02.html','rt',encoding='utf-8').read()
bsObject = bs4.BeautifulSoup(webPage,'html.parser')
tag_ul = bsObject.find('ul')
print(tag_ul)

tag_li = bsObject.findAll('li')
print(tag_li)