import pandas as pd
import csv
import matplotlib.pyplot as plt

# 한글 지원
from matplotlib import font_manager, rc
font_path = "C:\\Windows\\Fonts\\gulim.ttc"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)

figure = plt.figure()
ax = figure.add_subplot()
# open()으로 읽을 때
# f = open('download_file.csv', 'r', encoding='cp949')
# result = csv.reader(f)
# print(next(result)) #건바이 건으로 읽을 수 있다.
# print(next(result)) #건바이 건으로 읽을 수 있다.
# print(next(result)) #건바이 건으로 읽을 수 있다.
# for r in result: print(r) #반복문으로 읽을 때

# pandas에서 읽을 때
result = pd.read_csv('download_file.csv', encoding='cp949')
# print(result, type(result)) # <class 'pandas.core.frame.DataFrame'>
# print(result.columns)
# print(result.iloc[1,0])
# print(result.iloc[:,1])

gu = []; # 빈 리스트 생성
pop = result.iloc[:,1]
popluation = [int (i.replace(",","")) for i in pop]
for g in result.iloc[:,0]: # 인덱스 : iloc | 이름(칼럼명) : loc
  gu.append(str(g).split("  (")[0]) # 0번 인덱스에서 공백을 기준으로 0번째

# list 만 사용할 경우
# bar = ax.barh(gu,popluation)

gu_pop = list(zip(gu, popluation)) # 두 개의 객체를 합치고(zip) list 형태
df_main = pd.DataFrame(data=gu_pop)
df_main.columns = ['지역', '총 인구수']
print(df_main)

x = df_main.loc[:,'지역'].to_list()
y = df_main.iloc[:,1].to_list()
# print("pandas======================")
print(x, type(x))
print(y, type(y))

bar = ax.barh(x,y, label="y", color="pink")
# 가로바에서 숫자 넣기
for idx, value in enumerate(pop):
    plt.text(int(value.replace(",",""))+100, idx, str(value)) # +100 : 막대와 숫자 간격 넓혀줌

ax.set_xlim(0,61293934)
plt.rc(font)
plt.show()