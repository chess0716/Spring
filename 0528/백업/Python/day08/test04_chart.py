from selenium import webdriver as wd
from selenium.webdriver.common.by import By
import pandas as pd
import pymysql as py
import matplotlib.pyplot as plt

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'

con = py.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()
driver = wd.Chrome()
driver.implicitly_wait(2)
driver.get('https://www.melon.com/chart/index.htm')

tbody = driver.find_element(By.XPATH, '//*[@id="frm"]/div/table/tbody')
trs = tbody.find_elements(By.TAG_NAME, 'tr')

datas = []

for tr in trs:
    rank = tr.find_element(By.CLASS_NAME, 'rank').text
    title = tr.find_element(By.CSS_SELECTOR, 'div.ellipsis.rank01').find_element(By.TAG_NAME, 'a').text
    singer = tr.find_element(By.CSS_SELECTOR, 'div.ellipsis.rank02').find_element(By.TAG_NAME, 'a').text
    album = tr.find_element(By.CSS_SELECTOR, 'div.ellipsis.rank03').find_element(By.TAG_NAME, 'a').text
    like_text = tr.find_element(By.XPATH, './/td[8]/div/button/span[2]').text

   
    like = int(like_text.replace(',', ''))

    datas.append([rank, title, singer, album, like])

#좋아요가 5만이하 , 5만이상 ,8만이상 , 10만이상 --> 파이차트

cur.execute('SELECT * FROM melonsq WHERE melon_like <= 50000')
result50k = cur.fetchall()

cur.execute('SELECT * FROM melonsq WHERE melon_like > 50000 AND melon_like <= 80000')
result50k_80k = cur.fetchall()

cur.execute('SELECT * FROM melonsq WHERE melon_like > 80000 AND melon_like <= 100000')
result_80k_100k = cur.fetchall()

cur.execute('SELECT * FROM melonsq WHERE melon_like > 100000')
result_100k = cur.fetchall()

sizes = [len(result50k), len(result50k_80k), len(result_80k_100k), len(result_100k)]
labels = ['<= 50000', '> 50000 and <= 80000', '> 80000 and <= 100000', '> 100000']

plt.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=90)
plt.axis('equal')
plt.title('chart')
plt.show()
