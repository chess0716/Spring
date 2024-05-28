import bs4

webPage = open('HTML/Sample02.html','rt',encoding='utf-8').read()
bsObject = bs4.BeautifulSoup(webPage,'html.parser')

tag_li_all = bsObject.findAll('li')
for tag_li in tag_li_all:
    print(tag_li.text)
print()

for i in range(len(tag_li_all)): #i : index
    print(tag_li_all[i].text)