# req = requests.get('https://www.melon.com/chart/index.htm', headers=header)
#순위 제목 가수 앨범 pythondb스키마에 멜론 테이블 추가


#순위 곡명 가수 앨범  = > 출력

from bs4 import BeautifulSoup
import urllib.request
import pandas as pd
import pymysql 
#db셋팅

con = pymysql.connect(host ='127.0.0.1', user='root', password='chassisle1', db='pythondb', charset='utf8')
cur = con.cursor()
headers = {'User-Agent' : 'Mozilla/5.0'}
url = "https://www.melon.com/chart/index.htm"
request = urllib.request.Request(url, headers=headers)
htmlObject = urllib.request.urlopen(request)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

melontag = bsObject.select('#frm > div > table > tbody')

melondata = []

for tag in melontag:
    rank = tag.select_one('span.rank').text
    title = tag.select_one('div.ellipsis.rank01>span>a').text
    artist = tag.select_one('div.ellipsis.rank02 > a').text
    album = tag.select_one('div.rank03 > a').get_text
    melondata.append([rank,title, artist, album])

for data in melondata:
    rank, title, artist, album = data

sql = "INSERT INTO melon ('rank','title','artist', 'album')VALUES(%s, %s, %s, %s)"

cur.execute(sql,(rank,title,artist,album))
con.commit()
con.close()