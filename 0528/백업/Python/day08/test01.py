from bs4 import BeautifulSoup
import requests
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc

codes = ['252670', '251340']
stock_data = []

for code in codes:
    url = 'https://finance.naver.com/item/main.naver?code=' + code
    req = requests.get(url)
    soup = BeautifulSoup(req.content, 'html.parser')

    subject = soup.select_one('#middle > div.h_company > div.wrap_company > h2 > a').string
    point = soup.select_one('#chart_area > div.rate_info > div > p')

    # 개행 문자와 쉼표 제거 후 숫자로 변환 (정수로 변환)
    point_value = int(point.text.strip().replace(',', '').replace('\n', ''))
    stock_data.append({'subject': subject, 'point': point_value})

# 그래프 출력
subjects = [data['subject'] for data in stock_data]
points = [data['point'] for data in stock_data]

font_path = "C:/Windows/Fonts/malgun.ttf"
font_name = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font_name)

plt.bar(subjects, points)
plt.ylim([1000, 4000])
plt.xlabel('subject')
plt.ylabel('price')
plt.title('stock')
plt.show()


