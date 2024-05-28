from selenium import webdriver
from selenium.webdriver.common.by import By
import time

driver = webdriver.Chrome()
driver.implicitly_wait(2)
driver.get('https://tour.interpark.com/?mbn=tour&min=tour')

driver.maximize_window()



click_button = driver.find_element(By.ID, 'spHeaderInput')
click_button.click()

search_input = driver.find_element(By.ID, 'txtHeaderInput')

search_input.send_keys('제주도')


search_button = driver.find_element(By.ID, 'btnHeaderInput')
search_button.click()


time.sleep(5)

# 브라우저 닫기
driver.quit()


