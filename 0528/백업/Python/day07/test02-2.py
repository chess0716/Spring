import urllib.request
from bs4 import BeautifulSoup
import pymysql
import matplotlib.pyplot as plt

# MySQL 연결 정보
host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'


con = pymysql.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()


headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'}
url = "https://movie.daum.net/ranking/reservation"
request = urllib.request.Request(url, headers=headers)

htmlObject = urllib.request.urlopen(request)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

movietag = bsObject.select('#mainContent > div > div.box_ranking > ol > li > div > div.thumb_cont')
moviedata = []

# 평점이 9점 이상, 8점 이상, 6점 이상, 6점 미만인 영화 정보 추출
for tag in movietag:
    moviegrade = tag.select_one('span.txt_grade').text
    moviedata.append([moviegrade])


for data in moviedata:
    insert_query = "INSERT INTO daum_movie (`moviegrade`) VALUES (%s)"
    cur.execute(insert_query, (data[0]))


con.commit()


select_sql = "SELECT moviegrade FROM daum_movie "
cur.execute(select_sql)




moviegrades = [data[0] for data in moviedata]
counts = {grade: moviegrades.count(grade) for grade in set(moviegrades)}

labels = counts.keys()
sizes = counts.values()

plt.pie(sizes, labels=labels, autopct=lambda p: '{:.0f}'.format(p * sum(sizes) / 100), startangle=140)
plt.axis('equal')  
 
plt.title(' Movie Ratings')
plt.show()


con.close()

