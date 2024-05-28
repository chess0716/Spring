import pandas as pd

filename = 'singer2.csv'
df = pd.read_csv(filename , index_col=None, encoding='cp949')

df_sort1 = df.sort_values(by=['데뷔 일자'],axis=0)
print(df_sort1)