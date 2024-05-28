import pandas as pd
import matplotlib.pyplot as plt
import matplotlib as mpl


df = pd.read_csv('weather_pandas.csv', encoding='utf-8',
                 index_col='Area')


# print(df.loc[['서울','인천','대전','대구','광주','부산',]])
city_df = df.loc[['서울','인천','대전','대구','광주','부산',]]
font_name = mpl.font_manager.FontProperties(fname ='c:/Windows/fonts/malgun.ttf').get_name()
mpl.rc('font',family = font_name)
ax = city_df.plot(kind='line',title = '날씨', figsize=(12,7),fontsize=12
                  , legend=True)
ax.set_xlabel('Area')
ax.set_ylabel('Temperature,Humidity')

# 꺽은선 그래프
fig, ax1 = plt.subplots(figsize=(12, 7))
ax1.set_xlabel('지역')
ax1.set_ylabel('기온', color='tab:red')
ax1.plot(city_df.index, city_df['Temperature'], color='tab:red', marker='o', label='기온')
ax1.tick_params(axis='y', labelcolor='tab:red')
ax1.legend(loc='upper left')

# 막대 그래프용 새로운 figure 생성
fig2, ax2 = plt.subplots(figsize=(12, 7))
ax2.set_xlabel('지역')
ax2.set_ylabel('습도', color='tab:blue')
ax2.bar(city_df.index, city_df['Humidity'], alpha=0.5, color='tab:blue', label='습도')
ax2.tick_params(axis='y', labelcolor='tab:blue')
ax2.legend(loc='upper right')
plt.show()
