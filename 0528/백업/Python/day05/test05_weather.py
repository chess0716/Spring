from bs4 import BeautifulSoup
import urllib.request
import pandas as pd

url = "http://www.weather.go.kr/weather/observation/currentweather.jsp"
htmlObject = urllib.request.urlopen(url)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

weather = bsObject.select_one('#weather_table > tbody ')
weatherdata = []

for tr in weather.select('tr'):
    tds = tr.select('td')
   
    weatherArea = tds[0].text
    weatherTemp = tds[5].text
    weatherHumi = tds[-4].text
    
    

    weatherdata.append([weatherArea, weatherTemp, weatherHumi])
# csv
with open('weather.csv', 'w', encoding='utf-8') as file:
    file.write('지역,기온,습도\n')
    for item in weather.text:
        row = ','.join(item)
        file.write(row+',')




# pandas
df = pd.DataFrame(weatherdata, columns=['Area', 'Temperature', 'Humidity'])

df.to_csv('weather_pandas.csv', index=False, encoding='utf-8')

print('CSV 파일 생성 완료')
