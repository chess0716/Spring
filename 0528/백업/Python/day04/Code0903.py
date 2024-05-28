import bs4

webPage = open('HTML/Sample02.html','rt',encoding='utf-8').read()
# print(webPage)
bsObject = bs4.BeautifulSoup(webPage,'html.parser')
# print(bsObject)

tag_div = bsObject.find('div')
print(tag_div)
print(tag_div.text)
print(tag_div.string)