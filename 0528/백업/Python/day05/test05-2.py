from bs4 import BeautifulSoup
import urllib.request
import pandas as pd

url = "https://movie.daum.net/ranking/reservation"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

movie_selector = bsObject.select('#mainContent > div > div.box_ranking > ol > li > div > div.thumb_cont')


moviedata = []

for i, movie in enumerate(movie_selector, start=1):
    movietitle = movie.select_one('a.link_txt').get_text(strip=True)
    moviegrade = movie.select_one('span.txt_grade').string.strip()
    moviereser = movie.select_one('span.txt_num').get_text(strip=True)


    moviedata.append([i, moviegrade, movietitle, moviereser])


df = pd.DataFrame(moviedata, columns=['Ranking', 'Grade', 'Title', 'ReservationRatio'])


df.to_csv('movie_ranking_pandas.csv', index=False, encoding='utf-8')

print("CSV 파일이 생성되었습니다.")
