import matplotlib.pyplot as plt
import pandas as pd

# 한글 지원
from matplotlib import font_manager, rc
font_path = "C:\\Windows\\Fonts\\gulim.ttc"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)

# 1. 2022년과 2023년 고용비율을 바그래프로 출력하시오.
# 데이터 로드
bar_data = pd.read_csv('연도별_고용률_통계표.csv', encoding='cp949')

# '구분' 열을 인덱스로 설정
bar_data.set_index(bar_data.columns[0], inplace=True)

# 2022년과 2023년 고용비율을 선택
years = ['2022', '2023']
employment_rates = bar_data.loc[:, years]

# 바 그래프
ax = employment_rates.plot(kind='bar', figsize=(10, 6))
ax.set_ylabel('고용률 (%)')
ax.set_title('2022년과 2023년 고용률')
ax.set_ylim(0, 100)

# 각 바 위에 수치를 표시
for p in ax.patches:
    # 바의 높이(고용률)를 가져옵니다.
    value = p.get_height()
    # 바의 중간에 수치를 표시합니다.
    ax.annotate(f'{value:.1f}%', (p.get_x() + p.get_width() / 2, value), ha='center', va='bottom')
plt.show()

# 2. 2023년 나이별 고용비율을 파이 그래프로 출력
# 데이터 로드
df = pd.read_csv('연도별_고용률_통계표.csv', encoding='cp949', index_col=0)

# 2023년 데이터만 선택
data_2023_age = df.loc[['15~19세', '20~29세', '30~39세', '40~49세', '50~59세', '60세?이상'], '2023']

# 파이 그래프 출력
plt.figure(figsize=(8, 6))
data_2023_age.plot(kind='pie', autopct='%1.1f%%')
plt.title('2023년 나이별 고용 비율')
plt.ylabel('')
plt.axis('equal')
plt.tight_layout()
plt.show()

# 3. 15세 이상 고용률의 연도별 PLOT 그래프를 출력하시오.
# 데이터 로드
plot_data = pd.read_csv('연도별_고용률_통계표.csv', encoding='cp949')

# 15세 이상 고용률 추출
employment_rate = plot_data.iloc[0][1:]

# 연도별 PLOT 그래프 출력
plt.figure(figsize=(12, 6))
employment_rate.index = plot_data.columns[1:]
employment_rate.plot(marker='o', color='b', linestyle='-', linewidth=2, markersize=8)
plt.title('15세 이상 고용률의 연도별 변화')
plt.xlabel('연도')
plt.ylabel('고용률')
plt.xticks(rotation=45)
plt.tight_layout()
plt.show()