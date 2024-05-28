from selenium import webdriver as wd
from selenium.webdriver.common.by import By
import pandas as pd
import pymysql as py

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'

con = py.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()


create_table_sql = """
CREATE TABLE IF NOT EXISTS melonsq (
    melon_rank INT,
    title VARCHAR(255),
    singer VARCHAR(255),
    album VARCHAR(255),
    melon_like INT,
    PRIMARY KEY (melon_rank)
)
"""

cur.execute(create_table_sql)

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

print(datas)

insert_sql = "INSERT INTO melonsq(melon_rank, title, singer, album, melon_like) VALUES (%s, %s, %s, %s, %s)"

for data in datas:
    cur.execute(insert_sql, data)

con.commit()
con.close()
