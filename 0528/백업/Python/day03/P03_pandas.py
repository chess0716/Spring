import pandas as pd
import  numpy as np
df = pd.read_csv('movies.csv')
print(len(df))
print(df.shape)
print(df.head())
print(df.tail())
print('============================')


print(df.loc[:5, 'title'])

import matplotlib as plt


# 막대 그래프용 새로운 figure 생성
