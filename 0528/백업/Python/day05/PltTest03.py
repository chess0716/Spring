import pandas as pd
import matplotlib.pyplot as plt
from matplotlib import font_manager


font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = font_manager.FontProperties(fname=font_path, size=12)
font_name = font_prop.get_name()
plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 10

df = pd.read_csv('연도별_고용률_통계표.csv', encoding='cp949')


df.set_index('Unnamed: 0', inplace=True)


employment_rates = df.loc['고용률(15세?이상)']


years = df.columns
rates = employment_rates.values


plt.figure(figsize=(10, 6))
plt.plot(years, rates, marker='o', linestyle='-', color='blue')
plt.title('연도별 고용률(15세 이상)')
plt.xlabel('연도')
plt.ylabel('고용률(%)')
plt.grid(True)
plt.xticks(rotation=45)
plt.show()
