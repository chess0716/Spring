from selenium import webdriver
from chromedrivermanager import ChromeDriverManager
from selenium.webdriver.common.by import By
import time

# Chrome 드라이버 설치 및 가져오기
chrome_driver_path = ChromeDriverManager().install()

# Selenium 웹 드라이버 초기화
options = webdriver.ChromeOptions()
options.add_argument("--headless")  # Chrome을 헤드리스 모드에서 실행 (선택 사항)

# Chrome 드라이버 초기화 및 options 전달
driver = webdriver.Chrome(ChromeDriverManager().install(), options=options)

# Nate 뉴스 페이지 열기
nateurl = 'https://news.nate.com/recent?mid=n0100'
driver.get(nateurl)

# 페이지가 완전히 로드될 때까지 대기 (예: 5초)
time.sleep(5)

# 뉴스 기사 정보 수집
articles = driver.find_elements(By.CLASS_NAME, 'mlt01')

# 출력
print('##### 실시간 뉴스 속보 #####')
num = 1
for article in articles:
    subject = article.find_element(By.CLASS_NAME, 'tit').text
    link = article.find_element(By.CLASS_NAME, 'lt1').get_attribute('href')
    pressAndDate = article.find_element(By.CLASS_NAME, 'medium').text

    press, pDate, pTime = '', '', ''

    if len(pressAndDate.split()) == 3:
        press, pDate, pTime = pressAndDate.split()
    elif len(pressAndDate.split()) == 4:
        press1, press2, pDate, pTime = pressAndDate.split()
        press = press1 + press2
    else:
        continue

    print('(', num, ')', subject)
    print('\t', link, press, pDate, pTime)
    num += 1

# 브라우저 닫기
driver.quit()

  
