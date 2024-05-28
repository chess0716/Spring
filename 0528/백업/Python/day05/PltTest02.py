import pandas as pd
import matplotlib.pyplot as plt
from matplotlib import font_manager

font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = font_manager.FontProperties(fname=font_path, size=12)
font_name = font_prop.get_name()
plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 10

data = pd.read_csv('연도별_고용률_통계표.csv', encoding='cp949')
df = pd.DataFrame(data)
df.set_index(df.columns[0], inplace=True)


labels = df.index  # 인덱스를 라벨로 사용
sizes = df['2023']
colors = plt.cm.tab20c.colors  # 색상 지정

fig, ax = plt.subplots()
wedges, texts, autotexts = ax.pie(sizes, labels=labels, colors=colors, autopct='%1.1f%%', startangle=140, textprops=dict(color="w"))

# 범례 추가
ax.legend(wedges, labels, title="Categories", loc="center left", bbox_to_anchor=(1, 0, 0.5, 1))

# 그래프 타이틀 설정
ax.set_title('나이별 고용비율 파이그래프')

# 자동 텍스트 속성을 흰색에서 검은색으로 변경하여 가독성 향상
plt.setp(autotexts, size=8, weight="bold", color="black")

plt.show()
