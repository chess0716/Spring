from bs4 import BeautifulSoup
import urllib.request

urlDaum = 'https://news.daum.net/economic'
htmlObject = urllib.request.urlopen(urlDaum)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

tags_with_href = bsObject.select('[href]')

for tag in tags_with_href:
    href_value = tag['href']
    if 'v' in href_value:
        print(tag.text.strip(), href_value)
