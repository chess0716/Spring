from bs4 import BeautifulSoup
import urllib.request

url = "https://ko.wikisource.org/wiki/%EC%A0%80%EC%9E%90:%EC%9C%A4%EB%8F%99%EC%A3%BC"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage,'html.parser')

# 수정된 선택자
a_list = bsObject.select("#mw-content-text > div.mw-content-ltr.mw-parser-output > ul > li > a")

for a in a_list:
    name = a.string
    print(name)

