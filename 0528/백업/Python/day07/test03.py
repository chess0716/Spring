from bs4 import BeautifulSoup
import requests
import pymysql

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'

con = pymysql.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()

req = requests.get('https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108')
soup = BeautifulSoup(req.text, 'xml')

weatherdata = []
for tag in soup.select('body'):
    city = tag.select_one('city').text
    tmef = tag.select_one('tmEf').text
    wf = tag.select_one('wf').text
    tmn = tag.select_one('tmn').text
    tmx = tag.select_one('tmx').text
    weatherdata.append((city, tmef, wf, tmn, tmx))

insert_sql = "INSERT INTO forecast2 (city, tmef, wf, tmn, tmx) VALUES (%s, %s, %s, %s, %s)"
cur.executemany(insert_sql, weatherdata)

con.commit()
con.close()
