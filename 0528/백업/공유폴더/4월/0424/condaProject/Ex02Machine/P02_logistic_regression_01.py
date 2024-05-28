import pandas as pd

fish = pd.read_csv('https://bit.ly/fish_csv_data')
print(fish.head())
print(pd.unique(fish['Species']))

fish_input = fish[['Weight', 'Length', 'Diagonal', 'Height', 'Width']].to_numpy()
print(fish_input[0:5])
fish_target = fish['Species'].to_numpy()

from sklearn.model_selection import train_test_split
train_input, test_input, train_target, test_target = (
  train_test_split(fish_input, fish_target, random_state=42))

# 전처리 과정 : 근접할 때 가장 가까운 데이터 매칭 위함. 표준화, 평균 0, 분산을 1로 조정
from sklearn.preprocessing import StandardScaler
ss = StandardScaler()
ss.fit(train_input)
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

from sklearn.neighbors import KNeighborsClassifier
kn = KNeighborsClassifier(n_neighbors=3)
kn.fit(train_scaled, train_target)
print(kn.score(train_scaled, train_target)) # 0.8907563025210085
print(kn.score(test_scaled, test_target)) # 0.825

# 타깃 데이터 2개 이상의 클래스가 포함될 경우 : 다중분류라고 함.
print(kn.classes_) # 클래스가 알파벳 순으로 출력
print(kn.predict(test_scaled[:5])) # test의 첫 5줄에 대한 예측

import numpy as np
proba = kn.predict_proba(test_scaled[:5])
print(np.round(proba, decimals=4)) # 소수점 4자리까지 표기

# Logistic function의 그래프를 출력 확인
import matplotlib.pyplot as plt
z = np.arange(-5, 5, 0.1) # -5와 5 사이에 0.1 간격으로 배열 z를 만듦
phi = 1/(1 + np.exp(-z)) # z의 위치마다 로지스틱(시그모이드) 함수를 계산, np.exp()는 지수함수 계산
plt.plot(z, phi)
plt.xlabel('z')
plt.ylabel('phi')
#plt.show()

# 로지스틱 회귀 분류를 2진분류부터 수행하기
char_arr = np.array(['A', 'B', 'C', 'D', 'E'])
print(char_arr[[True, False, True, False, False]])

bream_smelt_indexes = (train_target == 'Bream') | (train_target == 'Smelt')
train_bream_smelt = train_scaled[bream_smelt_indexes]
target_bream_smelt = train_target[bream_smelt_indexes]
print(train_bream_smelt)
print(target_bream_smelt)

from sklearn.linear_model import LogisticRegression
lr = LogisticRegression()
lr.fit(train_bream_smelt, target_bream_smelt)
print(lr.predict(train_bream_smelt[:5]))
print(lr.predict_proba(train_bream_smelt[:5])) # 로지스틱 함수를 적용한 확률 출력
print(lr.classes_)
print(lr.coef_, lr.intercept_)
# logistic 모델이 학습한 방정식은 아래와 같다.
# z = -0.404*weight -0.576*length 0.663*diagonal -1.013*height -0.731*width -2.161 # 다중 속성
decisions = lr.decision_function(train_bream_smelt[:5])
print(decisions) # 처음 5개의 행에 대한 방정식을 적용한 값을 출력

from scipy.special import expit # 파이썬의 로지스틱 함수를 이용하여 출력된 값을 변환
print(expit(decisions))

# 로지스틱 회귀로 다중분류 수행하기
# 7개의 생선으로 분류
lr = LogisticRegression(C=20, max_iter=1000)
lr.fit(train_scaled, train_target)
print(lr.score(train_scaled, train_target)) # 0.9327731092436975
print(lr.score(test_scaled, test_target)) # 0.925
print(lr.predict(test_scaled[:5]))
proba = lr.predict_proba(test_scaled[:5])
print(np.round(proba, decimals=3))
print(lr.classes_)
print(lr.coef_, lr.intercept_.shape)
# 다중분류에서는 softmax를 사용하여 7개의 z값을 확률로 변환

