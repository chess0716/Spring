import pandas as pd 

df = pd.read_csv('apt_201910.csv',encoding='cp949',thousands=',')
print(len(df))
print(df.shape)
print(df.head())
print(df.tail())
print('============================')
print(df['면적']) #print(df.면적)
# 면적이 130보다 큰 거 출력
print(len(df[df['면적'] > 130]))
print('===========================')
# 단지명 , 가격 만 10개 출력
df_ = df.loc[:, ['단지명','가격']]
print(df_.head(10))


print(df.loc[:5,'가격'])

# 면적이 130 넘는 아파트의 가격 출력
df_f = df[df['면적'] > 130]
print(df_f.loc[:5, '가격'])
# 면적이 130 넘고 가격이 2억 미만인 아파트의 가격 출력


 
df_f = df[(df['면적'] > 130) & (df['가격'] < 20000)]


print(df_f['가격'])

df_s = df.sort_values(['가격'],ascending=False)
print(df_s.가격)
# 9억원을 초과하는 가격으로 거래된 단지명 가격 출력
df_9 = df[df['가격'] > 90000]
print(df_9.loc[:, ['단지명', '가격']])
# 열 추가 : 단가  가격 / 면적 = 단가
df['단가'] = df['가격'] / df['면적']
print(df.단가)

df1 = df.loc[:10, ('시군구','면적','단가')]
df1.to_csv('apt_output.csv', index=False, encoding='cp949')
print('save')