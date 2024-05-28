import pandas as pd
from matplotlib import font_manager

data = pd.read_csv('2023날씨.csv', encoding='cp949')
print(data)
print(f'{"데이터 전처리":=^20}')
data = data[['지점명','일시','기온(°C)']]
print(data['지점명']=='서울')
is_seoul = data['지점명']=='서울'
seoul = data[is_seoul]
print(seoul, len(seoul))
is_busan = data['지점명']=='부산'
busan = data[is_busan]
print(busan, len(busan))

temp = []
for i in seoul['일시']:
    temp.append(i.split(' ')[0])
lack_seoul = []
unique_temp = set(temp)
for i in unique_temp:
    if temp.count(i) != 24:
        lack_seoul.append(i)
print(lack_seoul)

temp = []
for i in seoul['일시']:
    temp.append(i.split(' ')[0])
lack_busan = []
unique_temp = set(temp)
for i in unique_temp:
    if temp.count(i) != 24:
        lack_busan.append(i)
print(lack_busan)

lack_data = list(set(lack_seoul+lack_busan))
for i in lack_data:
    seoul = seoul[seoul['일시'].str.split('').str[0] !=i]
    busan = busan[busan['일시'].str.split('').str[0] !=i]

print(len(seoul))
print(len(busan))

import pandas as pd
data = pd.read_csv('2023날씨.csv', encoding='cp949')
print(data)
print(data.dtypes)

print(f'{"데이터 전처리":=^20}')
data = data[['지점명', '일시', '기온(°C)']]
# print(data['지점명']=='서울')
is_seoul = data['지점명']=='서울'
seoul = data[is_seoul]
# print(seoul, len(seoul))
is_busan = data['지점명']=='부산'
busan = data[is_busan]
# print(busan, len(busan))

temp = []
for i in seoul['일시']:
  temp.append(i.split(' ')[0])
lack_seoul = []
unique_temp = set(temp)
for i in unique_temp:
  if temp.count(i) != 24:
    lack_seoul.append(i)
# print(lack_seoul)

temp = []
for i in busan['일시']:
  temp.append(i.split(' ')[0])
lack_busan = []
unique_temp = set(temp)
for i in unique_temp:
  if temp.count(i) != 24:
    lack_busan.append(i)
# print(lack_busan)

lack_data = list(set(lack_seoul+lack_busan))
for i in lack_data:
  seoul = seoul[seoul['일시'].str.split(' ').str[0] != i]
  busan = busan[busan['일시'].str.split(' ').str[0] != i]

print(len(seoul))
print(len(busan))

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = font_manager.FontProperties(fname=font_path, size=14)
font_name = font_prop.get_name()

plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 12


# 시간대 설정
time = ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00',
        '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00']

# 데이터프레임 초기화
zeros = np.zeros((24, 2))
df = pd.DataFrame(zeros, index=time, columns=['seoul', 'busan'])


unique_dates = seoul['일시'].str.slice(0, 10).unique()
hottest_times = []

for date in unique_dates:
    daily_data = seoul[seoul['일시'].str.startswith(date)]
    if len(daily_data) == 24:
        max_idx = daily_data['기온(°C)'].idxmax()
        max_time = daily_data.loc[max_idx, '일시']
        max_temp = daily_data.loc[max_idx, '기온(°C)']
        hottest_times.append((date, max_time, max_temp))

# 결과 출력
for record in hottest_times:
    print(f"날짜: {record[0]}, 최고 기온 시간: {record[1]}, 기온: {record[2]}°C")

df = pd.DataFrame(index=time, columns=['seoul', 'busan'])
for t in time:
    # 해당 시간대에 해당하는 기온 데이터를 필터링하여 평균을 계산
    df.loc[t, 'seoul'] = seoul[seoul['일시'].str.contains(f' {t}')]['기온(°C)'].mean()
    df.loc[t, 'busan'] = busan[busan['일시'].str.contains(f' {t}')]['기온(°C)'].mean()

# 데이터 시각화
plt.figure(figsize=(14, 10))
width = 0.35
y_pos = np.arange(len(df))


plt.barh(y_pos - width/2, df['seoul'], height=width, color='skyblue', label='Seoul')
plt.barh(y_pos + width/2, df['busan'], height=width, color='lightgreen', label='Busan')

plt.xlabel('평균 기온 (°C)')
plt.title('평균기온 비교 서울,부산')
plt.yticks(y_pos, time)
plt.legend()

plt.show()