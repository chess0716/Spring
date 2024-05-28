import datetime
from bs4 import BeautifulSoup
import requests
import time

nateUrl = "https://news.nate.com/weather?areaCode=11D20401"  # 속초
while True:
    htmlObject = requests.get(nateUrl)
    webPage = htmlObject.text
    bsObject = BeautifulSoup(webPage, 'html.parser')

    tag = bsObject.select_one('.right_today')
 

    temper = tag.select_one('p', {'class': 'celsius'}).text
    humi = tag.select_one('p', {'class': 'humidity'}).text
    rain = tag.select_one('p', {'class': 'rainfall'}).text
    wind = tag.select_one('p', {'class': 'wind'}).text

    now = datetime.datetime.now()
    yymmdd = now.strftime('%Y-%m-%d')
    hhmmss = now.strftime('%H-%M-%S')
    weather_list = [yymmdd, hhmmss, temper, humi, rain, wind]
    print(weather_list)

    time.sleep(10)