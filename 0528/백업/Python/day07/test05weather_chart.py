from bs4 import BeautifulSoup
import requests
import pymysql
import matplotlib.pyplot as plt

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

    # '부산'인 경우에만 데이터 추가
    if city == '부산':
        tmEf = data_tag.find('tmEf').text
        wf = data_tag.find('wf').text
        tmn = data_tag.find('tmn').text
        tmx = data_tag.find('tmx').text
        weatherdata.append((city, tmEf, wf, tmn, tmx))

# 부산의 날짜별 최저기온, 최고기온 plot 그래프
cur.execute("SELECT tmEf, tmn, tmx FROM forecast2 WHERE city = '부산'")
result = cur.fetchall()

x = []
y_min = []
y_max = []
for row in result:
    x.append(row[0])
    y_min.append(row[1])
    y_max.append(row[2])

plt.figure(figsize=(12,6))
plt.plot(x, y_min, label='tmn')
plt.plot(x, y_max, label='tmx')
plt.title('temp min, temp max')
plt.xticks(rotation=15)
plt.xlabel('date')
plt.ylabel('degree')
plt.legend()
plt.show()
# 부산 wf상태를 파이(pie) 차트로 출력
cur.execute("SELECT wf, COUNT(*) FROM forecast2 WHERE city = '부산' GROUP BY wf")
result_wf = cur.fetchall()

labels_wf = [row[0] for row in result_wf]
values_wf = [row[1] for row in result_wf]

plt.figure(figsize=(8, 8))
plt.pie(values_wf, labels=labels_wf, autopct='%1.1f%%', startangle=140)
plt.axis('equal') 
plt.title('Busan Weather Ratio')
plt.show()

con.close()
