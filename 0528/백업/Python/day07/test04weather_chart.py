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
for tag in soup.select('city'):
   if  tag.text== '부산':
    city = tag.text


    location_tag = tag.find_parent('body location data')
    tmef = location_tag.select_one('tmEf').text
    tmn = location_tag.select_one('tmn').text
    tmx = location_tag.select_one('tmx').text

    weatherdata.append((city, tmef, tmn, tmx))

print (weatherdata)
cur.execute("SELECT * FROM forecast2 WHERE city = '부산'")
datas = []
result_busan = cur.fetchone()
for row in result_busan:
    datas = [result_busan[2], result_busan[3], result_busan[4]]
print(datas)