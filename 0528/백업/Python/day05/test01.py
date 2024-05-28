
import re
from bs4 import BeautifulSoup
html = """
    <ul>
        <li><a href="hoge.html">hoge</li>
        <li><a href="https://example.com/fuga">fuga*</li>
        <li><a href="https://example.com/foo">foo*</li>
        <li><a href="https://example.com/foobbb">foobbb*</li>
        <li><a href="http://example.com/aaa">aaa</li>
    </ul>
"""
bsObject = BeautifulSoup(html,'html.parser')

lis = bsObject.find_all(href=re.compile(r'https://'))
print(lis)
for i in lis:
    print(i.attrs['href'])
print('===========================')
tag = re.findall(r'"http.+?"',html)

print(tag)