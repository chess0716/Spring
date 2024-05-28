import urllib.request
from bs4 import BeautifulSoup
import pandas as pd
import matplotlib.pyplot as plt
headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'}

melonurl = 'https://www.melon.com/chart/index.htm'
request = urllib.request.Request(melonurl, headers=headers)

htmlObject = urllib.request.urlopen(request)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

melontag = bsObject.select('#frm > div > table > tbody tr')

melondata = []


for tag in melontag:
    rank = tag.select_one('span.rank').text
    title = tag.select_one('div.ellipsis.rank01 >span > a').text
    artist = tag.select_one('div.ellipsis.rank02 > a').text
    album = tag.select_one('div.ellipsis.rank03 > a').text
    
    melondata.append([rank, title, artist, album])

df = pd.DataFrame(melondata, columns=['Rank', 'Title', 'Artist', 'Album'])
df.to_csv('melon_ranking.csv',index=False,encoding='utf-8-sig')
print('csv파일생성')
print(df)



