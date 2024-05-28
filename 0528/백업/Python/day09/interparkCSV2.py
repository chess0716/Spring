from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import matplotlib.pyplot as plt
import pandas as pd

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
                
      
            next_button = driver.find_element(By.CSS_SELECTOR, next_button_selector)
            next_button.click()

        

finally:
    driver.quit()

df = pd.DataFrame(datas,columns=['호텔 명','가격'])
df.to_csv('result.csv',index=False,encoding='utf-8-sig')

print('csv파일생성')
