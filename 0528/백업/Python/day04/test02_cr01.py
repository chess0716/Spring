from bs4 import BeautifulSoup

html ="""

    <html><body>
        <h1>스크레이핑이란></h1>
        <p>웹 페이지를 분석하는 것 </p>
        <p>원하는 부분을 추출하는 것 </p>
    </body></html>                      
"""
url : 'html'
bsObject = BeautifulSoup(html,'html.parser')

tag_h1 = bsObject.find('h1')

tag_p = bsObject.select('p')
print("h1 :",tag_h1)
print('p :',tag_p)

print(tag_h1.text)

for i in tag_p:
    print(i.string)

print(tag_p[1].text)

h1 = bsObject.html.body.h1
print('h1 >>>>>>>',h1)
p = bsObject.html.body.p            
print('p >>>',p)
p1 = p.nextSibling.nextSibling
print('p1 >>>',p1)
