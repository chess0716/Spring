import urllib.request
import bs4

nateUrl = 'https://www.nate.com'
htmlObject = urllib.request.urlopen(nateUrl)
# html = htmlObject.read()
bsObject = bs4.BeautifulSoup(htmlObject,'html.parser')

print(bsObject)

