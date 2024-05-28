from bs4 import BeautifulSoup
import urllib.request
import csv
import pandas as pd

url = "https://movie.daum.net/ranking/reservation"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

movie_selector = bsObject.select('#mainContent > div > div.box_ranking > ol > li > div > div.thumb_cont')


moviedata = []

with open('movie_ranking.csv', 'w', newline='', encoding='utf-8') as csvfile:
    csv_writer = csv.writer(csvfile)
    csv_writer.writerow(['Ranking', 'Grade', 'Title', 'ReservationRatio'])

    for i, movie in enumerate(movie_selector, start=1):
        movietitle = movie.select_one('a.link_txt').get_text(strip=True)
        moviegrade = movie.select_one('span.txt_grade').string.strip()
        moviereser = movie.select_one('span.txt_num').get_text(strip=True)

        csv_writer.writerow([i, moviegrade, movietitle, moviereser])
        moviedata.append([i, moviegrade, movietitle, moviereser])
        print(f"Writing {i}, {moviegrade}, {movietitle}, {moviereser} to CSV file")


dfmovie = pd.DataFrame(moviedata, columns=['Ranking', 'Grade', 'Title', 'ReservationRatio'])


dfmovie.to_csv('daummovie_pandas.csv', encoding='UTF-8-sig', index=False, header=['Ranking', 'Grade', 'Title', 'ReservationRatio'])
print("CSV 파일이 생성되었습니다.")


