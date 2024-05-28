import bs4
# Sample03.html 에서 div 추출

webPage = open('HTML/Sample03.html','rt',encoding='utf-8').read()
bsObject = bs4.BeautifulSoup(webPage,'html.parser')

tag_div = bsObject.findAll('div',{'class':'myClass1'})
print(tag_div)

tag_myClass3 = bsObject.findAll('li',{'class':'myClass3'})
print(tag_myClass3)

a_list = bsObject.findAll('a')
for aTag in a_list :
    print(a_list['href'])


