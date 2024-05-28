import csv
import time
import datetime
import bs4
import urllib.request

csvName = 'CSV/busan_weather.csv'
with open(csvName, 'w', newline='') as csvFp:
    csvWriter = csv.writer(csvFp)
    csvWriter.writerow(['연원일', '시분초', '온도', '습도', '강수량', '풍향'])

nateUrl = "https://news.nate.com/weather?areaCode=11H20201"
while True:
    htmlObject = urllib.request.urlopen(nateUrl)
    webPage = htmlObject.read()
    bsObject = bs4.BeautifulSoup(webPage, 'html.parser')

    # 수정: CSS 선택자를 사용하여 요소를 찾음
    tag = bsObject.select_one('#contentsWraper > div.weather_main_today_wrap > div.weather_today > div.today_wrap > div > div.right_today')
    temper = tag.select_one('p',{'class':'celsius'})
    humi = tag.select_one('div.hrw_area > p.humidity')
    rain = tag.select_one('div.hrw_area > p.rainfall')
    wind = tag.select_one('div.hrw_area > p.wind')

    now = datetime.datetime.now()
    yymmdd = now.strftime('%Y-%m-%d')
    hhmmss = now.strftime('%H-%M-%S')


    weather_list = [yymmdd, hhmmss, temper.text.strip(), humi.text.strip(), rain.text.strip(), wind.text.strip()]
    

    with open(csvName,'a',newline='') as csvFp:
        csvWriter = csv.writer(csvFp)
        csvWriter.writerow(weather_list)

    time.sleep(1)

