from selenium import webdriver
from bs4 import BeautifulSoup
import re



driver = webdriver.Chrome()
driver.get('https://www.melon.com/chart/index.htm')

driver.implicitly_wait(5)

page = driver.page_source
soup = BeautifulSoup(page, 'html.parser')

tbody = soup.select_one('#frm > div > table > tbody')
trs = tbody.select('tr')

datas = []

for tag in trs:
    rank = tag.select_one('span.rank').text
    title = tag.select_one('div.ellipsis.rank01 >span > a').text
    artist = tag.select_one('div.ellipsis.rank02 > a').text
    album = tag.select_one('div.ellipsis.rank03 > a').text
    likes = tag.select_one('span.cnt').text()
    likes = re.sub('\n총건수\n', '',likes)
    datas.append([rank, title, artist, album,likes])






print(datas)

driver.quit()
