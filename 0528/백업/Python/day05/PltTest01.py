import pandas as pd
import matplotlib.pyplot as plt
from matplotlib import font_manager


font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = font_manager.FontProperties(fname=font_path, size=14)
font_name = font_prop.get_name()
plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 12


data = pd.read_csv('연도별_고용률_통계표.csv', encoding='cp949')
df = pd.DataFrame(data)
df.set_index(df.columns[0], inplace=True)

# 시각화
fig, ax = plt.subplots(figsize=(10, 8))
x = range(len(df))
width = 0.35


rects1 = ax.bar(x, df['2022'], width, label='2022', color='skyblue')


rects2 = ax.bar([p + width for p in x], df['2023'], width, label='2023', color='lightgreen')

def add_labels(rects):
    for rect in rects:
        height = rect.get_height()
        ax.annotate(f'{height}',
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(0, 3),  # y 방향으로 약간 올림
                    textcoords="offset points",
                    ha='center', va='bottom')

add_labels(rects1)
add_labels(rects2)

# 축 설정
ax.set_ylabel('Rates')
ax.set_title('2022, 2023 고용비율')
ax.set_xticks([p + width / 2 for p in x])  # X 축 틱 설정
ax.set_xticklabels(df.index)  # X 축 라벨을 인덱스(범주 이름)로 설정
ax.legend()

plt.xticks(rotation=45)
plt.show()
