import pandas as pd

data_dic = {
    '2010': 350,
    '2019': 400,
    '2020': 1050,
    '2021': 2000,
    '2022': 1000,
    '2023': 2500
}

df = pd.DataFrame(list(data_dic.items()), columns=['year', 'sale'])
print(df)

df2 = pd.DataFrame([[82,45,78],[45,78,12]],
                   index = ['중간고사','기말고사'],
                   columns=['1반','2반','3반']
                   )
print(df2)
df3= pd.DataFrame([[20201101,'lee',90,95],
                   [20221222,'kim',50,75],
                   [20231204,'hong',100,50]],
                  columns=['학번','이름','중간고사','기말고사'])
print(df3)
print('중간고사 합계 : ',df3.중간고사.sum())
print('기말고사 합계 : ',df3.기말고사.sum())
# 컬럽에 총점 추가
df3['총점'] = df3.중간고사 +  df3.기말고사
print(df3)
print('시험 평균 :',df3['총점'].mean())
df4 = pd.DataFrame([[20201101,'lee',90,95],
                   [20221222,'kim',50,75],
                   [20231204,'hong',100,50]])
df4.columns = ['학번','이름','중간고사','기말고사']
print(df4)

# df3을 pandas03.csv 파일로 내보내기
df3.to_csv('pandas03.csv',index=False)
print('save')
#pandas03.csv 읽어와서 출력
df_read = pd.read_csv('pandas03.csv')
print(df_read)