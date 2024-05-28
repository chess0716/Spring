import pandas as pd
import matplotlib.pyplot as plt
from sklearn.neighbors import KNeighborsClassifier
import numpy as np
import common.constant as const

figure = plt.figure()
data = pd.read_csv('Fish.csv')

bream_length = data.loc[data.Species == 'Bream', ['Length2']].iloc[:,0].to_list()

bream_weight = data.loc[data.Species == 'Bream', ['Weight']].iloc[:,0].to_list()
ONES = len(bream_length)

smelt_length = data.loc[data.Species == 'Smelt', ['Length2']].iloc[:,0].to_list()

smelt_weight = data.loc[data.Species == 'Smelt', ['Weight']].iloc[:,0].to_list()
ZEROS = len(smelt_length)

### Test set 작성
fish_length = bream_length +smelt_length
fish_weight = bream_weight +smelt_weight

# print(np.column_stack(([1,2,3],[4,5,6])))
fish_data = np.column_stack((fish_length,fish_weight))
# print(fish_data,len(fish_data))
print(fish_data[:5])
fish_target = np.concatenate((np.ones(ONES),np.zeros(ZEROS)));
print(fish_target)

from sklearn.model_selection import train_test_split

# train_test_split은 기본적으로 25%를 테스트 세트로 추출 bream(35):smelt(14)=2.5:1
# 기본 나누는 비율로 인해 샘플링 편향이 발생 3.3:1의 비율로 테스트 데이터 추출
train_input,test_input,train_target,test_target = (
    train_test_split(fish_data,fish_target,random_state=42))
print(train_input.shape, test_input.shape)
print(train_target.shape, test_target.shape)

# (stratify : ~을 계층화하다) 속성을 통해 2.5:1의 비율로 테스트 데이터를 추출
# 샘플링 편향이 발생하지 않도록 조정, 테스트 데이터는 25%만!
train_input,test_input,train_target,test_target = (
    train_test_split(fish_data,fish_target, stratify=fish_target ,random_state=42))
print(train_input.shape, test_input.shape)
print(train_target.shape, test_target)

kn = KNeighborsClassifier()
kn.fit(train_input, train_target)
print(kn.score(test_input, test_target))
print(kn.predict([[25,150]]))   # 0 => 예측 실패 : 점과 점 사이의 직선거리를 따짐
distances, indexes = kn.kneighbors([[25,150]])
print("distances:{} / indexes:{}".format(distances, indexes))

# axis (0:row, 1:column)
mean = np.mean(train_input, axis=0)
std = np.std(train_input, axis=0)
print(mean, std)

# 전처리 작업 :: 표준 점수 적용
train_scaled = (train_input - mean) / std # 표준점수(Z점수) = 편차(점수-평균치) / 표준편차
print(train_scaled)

kn.fit(train_scaled, train_target)
mean2 = np.mean(test_input,axis=0)
std2 = np.std(test_input,axis=0)
test_scaled = (test_input-mean)/std
print(kn.score(test_scaled, test_target))
new = ([25,150] - mean) / std
print(kn.predict([new]))


# plt.scatter(train_scaled[:,0],train_scaled[:,1])
# # plt.scatter(test_input_length, test_input_weight,c=test_target,cmap='bwr')
# plt.scatter(new[0],new[1],marker="^",color="r")
# plt.xlabel('Length')
# plt.ylabel('Weight')
# # plt.xlim(0,1000)
# plt.colorbar(label='Target')
# plt.show()


# print("distances:{} / indexes:{}".format(distances, indexes))

plt.scatter(train_scaled[:,0], train_scaled[:,1])
plt.scatter(new[0], new[1], marker="^", color="r")
plt.scatter(train_scaled[indexes, 0], train_scaled[indexes, 1], marker="D", color = "#000000")
plt.xlabel("length")
plt.ylabel("height")
plt.show()