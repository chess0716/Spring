import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from sklearn.neighbors import KNeighborsClassifier

# 데이터 로드
data = pd.read_csv('Fish.csv')

# 브림, 스멜트, 로치 데이터 추출
bream_data = data[data['Species'] == 'Bream']
smelt_data = data[data['Species'] == 'Smelt']
roach_data = data[data['Species'] == 'Roach']

# 브림, 스멜트, 로치의 길이와 무게 데이터 추출
bream_length = bream_data['Length2'].tolist()
bream_weight = bream_data['Weight'].tolist()

smelt_length = smelt_data['Length2'].tolist()
smelt_weight = smelt_data['Weight'].tolist()

roach_length = roach_data['Length2'].tolist()
roach_weight = roach_data['Weight'].tolist()

# 테스트 및 훈련 세트 생성
fish_length = bream_length + smelt_length + roach_length
fish_weight = bream_weight + smelt_weight + roach_weight

fish_data = [[l, w] for l, w in zip(fish_length, fish_weight)]
fish_target = [1] * len(bream_data) + [0] * len(smelt_data) + [2] * len(roach_data)

np_fish_data = np.array(fish_data)
np_fish_target = np.array(fish_target)

# 데이터 셔플링
np.random.seed(42)
index = np.random.permutation(len(np_fish_target))
shuffled_data = np_fish_data[index]
shuffled_target = np_fish_target[index]

# 훈련 및 테스트 세트 분할
train_input = shuffled_data[:int(0.7 * len(shuffled_data))]
train_target = shuffled_target[:int(0.7 * len(shuffled_target))]
test_input = shuffled_data[int(0.7 * len(shuffled_data)):]
test_target = shuffled_target[int(0.7 * len(shuffled_target)):]

# KNN 모델 생성 및 훈련
knn = KNeighborsClassifier()
knn.fit(train_input, train_target)

# 모델 성능 평가
score = knn.score(test_input, test_target)
print(f'테스트 세트 정확도: {score:.2f}')

# 산점도 그리기

plt.figure(figsize=(10, 6))
plt.scatter(bream_length, bream_weight, color='blue', label='Bream')
plt.scatter(smelt_length, smelt_weight, color='red', label='Smelt')
plt.scatter(roach_length, roach_weight, color='green', label='Roach')
plt.title('Fish Length vs Weight')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.show()

knn.fit(train_input,train_target)
print(knn.predict([[25,150]]))
print(knn.predict([[50,600]]))
print(knn.predict([[5,50]]))























