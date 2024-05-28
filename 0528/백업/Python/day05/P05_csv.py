import csv
import pandas as pd

import  matplotlib.pyplot as plt
from matplotlib import  font_manager, rc
# 오픈으로 읽을 때
f = open('202403_202403_주민등록인구및세대현황_월간.csv','r',encoding='cp949')
result = csv.reader(f)
print(result)
for r in result:
    print(r)
print('============================')

df = pd.read_csv('202403_202403_주민등록인구및세대현황_월간.csv',encoding='cp949',thousands=',')
print(len(df))
print("shape :"+ str(df.shape))
print("head :"+ str(df.head))
print("tail :"+ str(df.tail))
print('============================')
print(df["행정구역"])
df_ = df.loc[:,['행정구역','2024년03월_남여 비율']]
print(df_.head(10))

print((df.loc[:5,'2024년03월_남여 비율']))
df_1 = df[df['2024년03월_남여 비율']>1]
print(df_1.loc[:,['행정구역']])
df_2 = df_1.loc[:,['행정구역']]

df_2.to_csv('남녀비율_1.0_이상_지역.csv',index=False,encoding='cp949')
print('save')
print('============================')
print(df,type(df))
print(df.iloc[1,0])
print(df.iloc[:,0])
gu = []; population = df.iloc[:,1]
for g in df.iloc[:,0]:
    gu.append(str(g).split(" (")[0])
print(gu)
print(population,type(population))

# 막대 그래프 작성
font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = font_manager.FontProperties(fname=font_path, size=8)
font_name = font_prop.get_name()

plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 10
plt.barh(range(len(population)), population, color='red', label="인구 수")
plt.xlabel('구')
plt.ylabel('인구')
plt.xticks(population)
plt.yticks(range(len(population)), gu)
plt.title('지역별 인구')
plt.legend()
plt.show()
