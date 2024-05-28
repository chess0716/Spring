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
for data_tag in soup.select('body location data'):
    city = data_tag.find_previous('city').text
    tmEf = data_tag.find('tmEf').text
    wf = data_tag.find('wf').text
    tmn = data_tag.find('tmn').text
    tmx = data_tag.find('tmx').text
    weatherdata.append((city, tmEf, wf, tmn, tmx))

insert_sql = "INSERT INTO forecast2 (city, tmef, wf, tmn, tmx) VALUES (%s, %s, %s, %s, %s)"
cur.executemany(insert_sql, weatherdata)

con.commit()
con.close()






