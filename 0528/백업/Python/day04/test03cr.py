from bs4 import BeautifulSoup
html = """
    <html><body>
        <div id="meigen">
            <h1>위키북스 도서</h1>
            <ul class="items">
                <li>유니티 게임 이펙트 입문</li>
                <li>스위프트로 시작하는 아이폰 앱 개발 교과서</li>
                <li>모던 웹사이트 디자인의 정석</li>
            </ul>
        </div>
    </body></html>
"""
url : 'html'
bsObject = BeautifulSoup(html,'html.parser')

tag_h1 = bsObject.find('h1').text
print(tag_h1)

tag_h1 = bsObject.select_one('h1').text
print(tag_h1)

tag_li = bsObject.select('li')
for i in tag_li:
    print(i.text)

