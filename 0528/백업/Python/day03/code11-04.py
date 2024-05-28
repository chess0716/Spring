import pandas as pd
import numpy as np

data1 = np.arange(9).reshape((3, 3))
data2 = np.arange(12).reshape((4, 3))
df1 = pd.DataFrame(data1, columns=list('가나다'), index=['서울', '부산', '광주'])
df2 = pd.DataFrame(data2, columns=list('가다라'), index=['고양', '서울', '광주', '대전'])
print(df1, '\n')
print(df2, '\n')

newDf = df1 + df2
print(newDf)
