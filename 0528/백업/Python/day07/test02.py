from bs4 import BeautifulSoup
import requests

req = requests.get('https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108')
soup = BeautifulSoup(req.text, 'xml')

for tag in soup.find_all(True):
    print(tag.name, tag.text)


