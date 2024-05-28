from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import matplotlib.pyplot as plt
import pandas as pd
import pymysql as py

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'

create_table = """
CREATE TABLE IF NOT EXISTS hotel1 (
    num INT AUTO_INCREMENT PRIMARY KEY,
    hotelName VARCHAR(255),
    hotelPrice VARCHAR(255)
);

"""
con = py.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()

cur.execute(create_table)

driver = webdriver.Chrome()
driver.implicitly_wait(2)
driver.get('https://search-travel.interpark.com/search?q=%EC%A0%9C%EC%A3%BC%EB%8F%84&cateCode=tourH')

next_button_selector = '#app > div > div:nth-child(1) > div > div.contentsZone > div.panelZone > div.pageNumBox > button.nextBtn'
datas = []

try:
    for _ in range(10):  
       
            
            time.sleep(5)

            hotel_elements = driver.find_elements(By.XPATH, '//*[@id="boxList"]/li/div/div[2]/div[2]')
            for hotel_element in hotel_elements:
            
                hotel_name = hotel_element.find_element(By.XPATH, 'div[1]/a/h5').text
                hotel_price_text = hotel_element.find_element(By.XPATH, 'div[2]/div/p/strong').text
              
                hotel_price = int(hotel_price_text.replace(',', ''))
                print(f'호텔 명: {hotel_name}, 가격: {hotel_price}')
                
                datas.append((hotel_name,hotel_price))
            next_button = driver.find_element(By.CSS_SELECTOR, next_button_selector)
            next_button.click()

        

finally:
    driver.quit()


insert_sql = "INSERT INTO hotel1 (hotelName, hotelPrice) VALUES (%s, %s)"
for data in datas:
    cur.execute(insert_sql, data)

con.commit()
print('데이터베이스에 저장 완료')
con.close()
