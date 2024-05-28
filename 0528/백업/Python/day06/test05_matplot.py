import matplotlib.pyplot as plt
# add_subplot  (특정위치)
# add_subplot([총 행의 수],[총 열의 수],[subplot 번호])

figure = plt.figure()
axe1 = figure.add_subplot(2,2,1)
axe2 = figure.add_subplot(2,2,2)
axe3 = figure.add_subplot(2,2,3)
axe4 = figure.add_subplot(2,2,4)
axe1.plot([0,2]) # y축 데이터
axe2.plot([1,1])
axe3.plot([2,0])
axe4.plot([1,2])
# plt.show()
####################
# subplots(): figure 와 subplot 동시 생성
# subplots (생성할 subplot 행, 열)
figure , axes = plt.subplots(2,2) 
axes [0][0].plot([1,1])
axes [1][1].plot([1,2])
# plt.show()
figure = plt.figure()
axes = figure.add_subplot(224)
x = [0,2,4,6]
y = [0,4,0,2]
x2 = [0,1,2,3,4,5,6]
y2 = [1,2,3,4,5,6,7]
axes.plot(x,y)
axes.plot(x2,y2, color = 'k',linestyle='dashed',marker='o')
# plt.show()
#####
figure = plt.figure()
axes = figure.add_subplot(111)
x = [1,2,3,4]
y = [1,4,3,2]
x2 = [11,12,13,14]
y2 = [11,14,13,12]
axes.bar(x,y)
axes.bar(x2,y2)
plt.show()
##################
figure = plt.figure()
axes = figure.add_subplot(111)
x = [10,15,30,20]
y = ['1a','2b','3c','4d']
axes.pie(x,labels=y,autopct='%.1f%%')
plt.show()