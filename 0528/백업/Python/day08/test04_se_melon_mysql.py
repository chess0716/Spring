from selenium import webdriver as wd
from selenium.webdriver.common.by import By
import pandas as pd

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
    like = tr.find_element(By.XPATH, './/td[8]/div/button/span[2]').text

    datas.append([rank, title, singer, album, like])

print(datas)

df = pd.DataFrame(datas,columns=['순위','제목','가수','앨범','좋아요 수'])
df.to_csv('melon_like.csv',index=False,encoding='utf-8-sig')

print('csv파일생성')