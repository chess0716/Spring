import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

inFilename = 'singer.xlsx'
outFilename = 'singer_out.xlsx'
# 워크시트를 읽어 데이터프레임으로 만듬
df_senior = pd.read_excel(inFilename, 'senior', index_col=None)
df_junior = pd.read_excel(inFilename, 'junior', index_col=None)
# 데이터프레임 합침
df_singer = pd.concat([df_senior, df_junior])
# 추출후 데이터프레임 지정
df_singer_out = df_singer[df_singer['인원'] >= 6]
#인원이 많은순으로 정렬 false : 내림차순
df_singer_out = df_singer_out.sort_values(['인원'], axis=0, ascending=False)
# 원하는 열만 추출
df_singer_out = df_singer_out.loc[:, ['아이디', '이름', '인원', '유튜브 조회수']]
# x축 , y축 지정
x_data = df_singer_out['아이디']
y_data = df_singer_out['인원']
# 컬러 무작위 추출
colorAry = [np.random.choice(['red', 'green', 'blue', 'brown', 'gold', 'lime', 'aqua', 'magenta', 'purple']) for _ in
            range(len(x_data))]

plt.bar(x_data, y_data, color=colorAry)
plt.show()

# 엔진을 'openpyxl'로 지정하여 Excel 파일에 저장
with pd.ExcelWriter(outFilename, engine='openpyxl') as writer:
    df_singer_out.to_excel(writer, sheet_name='singer', index=False)

print('save. OK~')
#458 페이지


