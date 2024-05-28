# weather.csv 이나 weather_pandas.csv 를 읽어서 출력
import pandas as pd


df = pd.read_csv('weather_pandas.csv', encoding='utf-8')


print(df)
print(df['Temperature'].mean())
print(df['Humidity'].mean())
