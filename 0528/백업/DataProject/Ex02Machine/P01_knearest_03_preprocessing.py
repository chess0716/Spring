import numpy as np
import matplotlib.pyplot as plt
import  pandas as pd
from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split

# 상수 정의
BREAM_COUNT = 35
SMELT_COUNT = 14
ROACH_COUNT = 20

# 데이터 로드
data = pd.read_csv('Fish.csv')
bream_data = data[data['Species'] == 'Bream']
smelt_data = data[data['Species'] == 'Smelt']
roach_data = data[data['Species'] == 'Roach']

# 데이터 추출 및 합치기
bream_length = bream_data['Length2'].tolist()
bream_weight = bream_data['Weight'].tolist()
smelt_length = smelt_data['Length2'].tolist()
smelt_weight = smelt_data['Weight'].tolist()
roach_length = roach_data['Length2'].tolist()
roach_weight = roach_data['Weight'].tolist()

fish_length = bream_length + smelt_length + roach_length
fish_weight = bream_weight + smelt_weight + roach_weight

fish_data = np.column_stack((fish_length, fish_weight))
fish_target = np.concatenate((np.ones(BREAM_COUNT), np.zeros(SMELT_COUNT), np.full(ROACH_COUNT, 2)))

# 훈련 및 테스트 데이터 분할
X_train, X_test, y_train, y_test = train_test_split(fish_data, fish_target, stratify=fish_target, random_state=42)

# KNN 모델 생성 및 훈련
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)

print(knn.predict([[25,150]]))
print(knn.predict([[50,600]]))
print(knn.predict([[5,50]]))
# 결정 경계 시각화
x_min, x_max = X_train[:, 0].min() - 1, X_train[:, 0].max() + 1
y_min, y_max = X_train[:, 1].min() - 100, X_train[:, 1].max() + 100
xx, yy = np.meshgrid(np.linspace(x_min, x_max, 500), np.linspace(y_min, y_max, 200))
Z = knn.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)

plt.figure(figsize=(10, 6))
plt.contourf(xx, yy, Z, alpha=0.3)  # 경계를 더 미세하게
plt.scatter(25, 150, s=100, marker='o', color='yellow', label='New Data')
plt.scatter(bream_data['Length2'], bream_data['Weight'], color='blue', marker='o', label='Bream')
plt.scatter(smelt_data['Length2'], smelt_data['Weight'], color='red', marker='s', label='Smelt')
plt.scatter(roach_data['Length2'], roach_data['Weight'], color='green', marker='^', label='Roach')
plt.title('Fish Length vs Weight with Decision Boundary')
plt.xlabel('Length (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.grid(True)
plt.show()

# axis (0:row , 1: column)
mean = np.mean(X_train,axis=0)
std = np.std(X_train,axis=0)
print(mean,std)

train_scaled = (X_train - mean) / std # 표준점수 (z점수) = 편차(점수-평균치) / 표준편차
train_scaled2 = np.round(train_scaled, 2)
print(train_scaled2,len(train_scaled2))


knn.fit(train_scaled2, y_train)
test_scaled = (X_test - mean) / std
print(knn.score(test_scaled, y_test))

new = ([25,150] - mean) /std

plt.scatter(train_scaled2[:,0],train_scaled2[:,1])
plt.scatter(new[0],new[1],marker="^",color='r')
plt.xlabel('Length (cm)')
plt.ylabel('Weight (g)')
plt.show()