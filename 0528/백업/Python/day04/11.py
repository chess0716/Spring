import datetime
from bs4 import BeautifulSoup
import requests
import time


nateUrl = "https://news.nate.com/weather?areaCode=11D20401" #속초
while True:
    htmlObject =requests.get(nateUrl)
    webPage = htmlObject.text
  
    bsObject = BeautifulSoup(webPage, 'html.parser')

    # 온도
    # 습도
    # 강수량
    # 풍향
    tag = bsObject.select_one('div', {'class': 'right_today'})
    
    
    temper = tag.select_one('p', {'class': 'celsius'})
    humi = tag.select_one('p', {'class': 'humidity'})
    rain = tag.select_one('p', {'class': 'rainfall'})
    wind = tag.select_one('p', {'class': 'wind'})


    now = datetime.datetime.now()
    yymmdd = now.strftime('%Y-%m-%d')
    hhmmss = now.strftime('%H-%M-%S')
    weather_list = [yymmdd, hhmmss, temper, humi, rain, wind]
    print(weather_list)
 
    time.sleep(10)