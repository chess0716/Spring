# Stoachastic Gradient Descent
# (Stochastics: 주가나 환율의 마감 가격이 일정기간동안 어느 곳에 있었는지를 관찰하기 위해 백분율로 나타낸 단기 기술적 지표
import pandas as pd
from pymongo import MongoClient
# MongoDB 연결
client = MongoClient('mongodb://localhost:27017/')
db = client['fish_database']
collection = db['fish_collection']

# MongoDB에서 데이터 로드
cursor = collection.find({}, {'_id': 0, 'Species': 1, 'Weight': 1, 'Length1': 1, 'Length2': 1, 'Length3': 1, 'Height': 1, 'Width': 1})
fish_data = pd.DataFrame(list(cursor))

# 필요없는 '_id' 컬럼 제거 및 컬럼 이름 변경
fish_data.rename(columns={'Length2': 'Length', 'Length3': 'Diagonal'}, inplace=True)
fish_data.drop(columns=['Length1'], inplace=True)

# 입력 데이터와 타겟 데이터 설정
fish_input = fish_data[['Weight', 'Length', 'Diagonal', 'Height', 'Width']].to_numpy()
fish_target = fish_data['Species'].to_numpy()

from sklearn.model_selection import train_test_split
train_input, test_input, train_target, test_target = train_test_split(fish_input, fish_target, random_state=42)

from sklearn.preprocessing import StandardScaler
ss = StandardScaler()
ss.fit(train_input)
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

from sklearn.linear_model import SGDClassifier
# tol=None는 SGDClassifier가 최솟값을 자동으로 멈추지만 None으로 해서 100번 끝까지 진행
sc = SGDClassifier(loss='log_loss', max_iter=10, tol=None, random_state=42)
sc.fit(train_scaled, train_target)
print(sc.score(train_scaled, train_target)) # 0.773109243697479
print(sc.score(test_scaled, test_target)) # 0.775

sc.partial_fit(train_scaled, train_target) # sc.fit()을 이어서 훈련하고자 할 때
print(sc.score(train_scaled, train_target)) # 0.8151260504201681
print(sc.score(test_scaled, test_target)) # 0.85

import numpy as np
sc = SGDClassifier(loss='log_loss', random_state=42)
train_score = []
test_score = []
classes = np.unique(train_target)
for _ in range(0, 300):
  sc.partial_fit(train_scaled, train_target, classes=classes)
  train_score.append(sc.score(train_scaled, train_target))
  test_score.append(sc.score(test_scaled, test_target))

import matplotlib.pyplot as plt
plt.figure(figsize=(10, 6))
plt.plot(range(1, 301), train_score, label='train_set')
plt.plot(range(1, 301), test_score, label='test_set')
plt.xlabel('epoch')
plt.ylabel('score')
plt.legend()
plt.show()