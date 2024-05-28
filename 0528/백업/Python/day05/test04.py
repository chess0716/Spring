from bs4 import BeautifulSoup
import urllib.request
import csv

url = "https://movie.daum.net/ranking/reservation"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage,'html.parser')

movie_selector = '#mainContent > div > div.box_ranking > ol > li:nth-child({}) > div > div.thumb_cont'

for i in range(1, 21):
    movie = movie_selector.format(i)
    tag = bsObject.select_one(movie)
    text = tag.text
    print(i, text)

with open('movie_ranking.csv', 'w', newline='', encoding='utf-8') as csvfile:
    
    csv_writer = csv.writer(csvfile)


    csv_writer.writerow(['Ranking', 'Title'])

    for i in range(1, 21):
        movie = movie_selector.format(i)
        tag = bsObject.select_one(movie)
        text = tag.text.strip()

        
        csv_writer.writerow([i, text])
        print(f"Writing {i}, {text} to CSV file")

print("CSV 파일이 생성되었습니다.")