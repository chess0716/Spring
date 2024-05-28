'''
데이터시각화 :데이터 분석 결과를 시각적으로 ㅍ현하고 전달하는 과정
'''

import  matplotlib.pyplot as plt
import numpy as np
from matplotlib import  font_manager, rc

x = [1,2,3,4]
y = [2,3,4,5]
#plt.bar(x,y)
#plt.show()

figure = plt.figure()
axes1 = figure.add_subplot(221)
axes2 = figure.add_subplot(222)
axes3 = figure.add_subplot(223)
axes4 = figure.add_subplot(224)

axes1.plot([0,2])
axes2.plot([2,0])
axes3.plot([2,0])
axes4.plot([0,2])


plt.show()

x = [1, 2, 3, 4]
y = [2, 4, 6, 8]
x2 = [1, 2, 3, 4]
y2 = [4, 4, 3, 6]

# 중복되지 않는 x값들을 찾아서 하나의 리스트로 합칩니다.
tmp = np.unique(x + x2)
# x와 x2의 값들을 정렬합니다.
tmp.sort()
# 새로운 x값들의 인덱스를 생성합니다.
xlabel = np.arange(len(tmp))
width = 0.35

fig, ax = plt.subplots()
# 첫 번째 그래프를 그립니다.
ax.bar(xlabel - width / 2, y, width, label="x")
# 두 번째 그래프를 그립니다.
ax.bar(xlabel + width / 2, y2, width, label="x2")
ax.set_xticks(xlabel)
ax.set_xticklabels(tmp)
ax.legend()
plt.show()
'''
복합 축 (bar and plot)
'''
figure = plt.figure()
axes = figure.add_subplot(111)
axes2 = axes.twinx()
x = [1, 2, 3, 4]
y = [2, 4, 6, 8]
x2 = [1, 2, 3, 4]
y2 = [4, 4, 3, 6]

axes.bar(x,y,color="green", label='bar')
axes2.plot(x2,y2,color="red",label="plot")

axes.legend()
axes2.legend(loc=1)
plt.show()

'''
Scatter (산점도) graph
'''
figure = plt.figure()
axes = figure.add_subplot(111)
x = [1,2,3,4]
y = [2,4,6,8]
x2 = [1,1,3,4]
y2 = [6,2,4,6]

axes.scatter(x,y)
axes.scatter(x2,y2)
plt.show()
'''
Pie graph
'''
'''
figure = plt.figure()
axes = figure.add_subplot(111)
label = ['a','b','c','d']
data = [2,4,6,8]

axes.pie(data,labels=label)

font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = font_manager.FontProperties(fname=font_path, size=14)
font_name = font_prop.get_name()

plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 12

ration = [34,32,16,18]
label = ['하나','Banana','Melon','Grapes']
axes.pie(ration,labels=label,autopct='%.1f%%')
plt.show()
'''

'''
Word Cloud
'''
import wordcloud

with open('노무현_한·일관계에 대한 특별 담화문.txt', 'r', encoding='utf-8') as file:
    data = file.read()

wc = wordcloud.WordCloud(font_path = 'c:/Windows/fonts/malgun.ttf')

wc.generate(data)
wc.to_image()
wc.to_file('Roh.jpg')
