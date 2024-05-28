from bs4 import BeautifulSoup
import requests

movieUrl = 'https://movie.daum.net/ranking/reservation'
htmlObject = requests.get(movieUrl)
webPage = htmlObject.text
bsObject = BeautifulSoup(webPage, 'html.parser')

# 1부터 20까지의 순차적인 li 요소에 대한 선택자
movie = '#mainContent > div > div.box_ranking > ol > li:nth-child({}) > div > div.thumb_cont'

for i in range(1, 21):
    ranking = movie.format(i)
    tag = bsObject.select_one(ranking)

text = tag.text.strip()
print(i,text)
