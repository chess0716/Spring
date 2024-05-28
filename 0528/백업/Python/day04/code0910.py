import urllib.request
import bs4

nateUrl = 'https://www.nate.com'
htmlObject = urllib.request.urlopen(nateUrl)
webPage = htmlObject.read()
bsObject = bs4.BeautifulSoup(webPage,'html.parser')

tag = bsObject.find('div',{'id':'NateBi'})
print(tag, '\n')

a_tag = tag.find('a')
print(a_tag,'\n')
#href 값 추출

href = a_tag['href']
print(href,'\n')

text = a_tag.text
print(text)

