from selenium import webdriver as wd
from selenium.webdriver.common.by import By
import pymysql as py
import matplotlib.pyplot as plt

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'

con = py.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()

# 호텔명, 성급, 금액, 별점
driver = wd.Chrome()
driver.implicitly_wait(2)
driver.get('https://www.hanatour.com/all-search?page=1&tab=hotel&keyword=%EC%A0%9C%EC%A3%BC%EB%8F%84&pageSize=20')

selector = driver.find_elements(By.XPATH, '//*[@id="contents"]/div[3]/div[2]/div[2]/div[1]/ul/li/div/div[2]')
datas = []

for hotel_info in selector:
    hotelName = hotel_info.find_element(By.XPATH, 'div[1]/strong').text
    hotelStar = hotel_info.find_element(By.XPATH, 'div[2]/span[2]').text
    hotelCharge = hotel_info.find_element(By.XPATH, 'div[5]/div/div/strong').text
    hotelReview = float(hotel_info.find_element(By.XPATH, 'div[4]/strong').text)
    datas.append([hotelName, hotelStar, hotelCharge, hotelReview])


insert_sql = "INSERT INTO tour (hotelName, hotelStar, hotelCharge, hotelReview) VALUES (%s, %s, %s, %s)"
for data in datas:
    cur.execute(insert_sql, data)

con.commit()


cur.execute('SELECT * FROM tour WHERE hotelReview > 4')
rating_high = cur.fetchall()

cur.execute('SELECT * FROM tour WHERE hotelReview <= 4')
rating_low = cur.fetchall()

sizes = [len(rating_high), len(rating_low)]
labels = ['raiting high 4', 'rating lower 4']

plt.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=90)
plt.axis('equal')
plt.title('ratingchart')
plt.show()

con.close()






