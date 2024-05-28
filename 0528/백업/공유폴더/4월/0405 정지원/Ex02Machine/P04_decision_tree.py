# Decision Tree
import pandas as pd
wine = pd.read_csv('https://bit.ly/wine_csv_data')
print(wine.head()) # alcohol sugar pH(산도) class(0:레드, 1:화이트)
print(wine.info)
print(wine.describe())

import numpy as np
data = wine[['alcohol', 'sugar', 'pH']].to_numpy()
target = wine['class'].to_numpy()

from sklearn.model_selection import train_test_split
train_input, test_input, train_target, test_target = train_test_split(data, target, random_state=42, test_size=0.2)
print(train_input.shape, test_input.shape)

from sklearn.preprocessing import StandardScaler
ss = StandardScaler()
ss.fit(train_input) # 훈련
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

from sklearn.linear_model import LogisticRegression
lr = LogisticRegression()
lr.fit(train_scaled, train_target)
print(lr.score(train_scaled, train_target)) # 0.7808350971714451
print(lr.score(test_scaled, test_target)) # 0.7776923076923077 # 과대적합
print(lr.coef_, lr.intercept_) # 가중치(coefficient)와 절편(intercept)

from sklearn.tree import DecisionTreeClassifier
dt = DecisionTreeClassifier(random_state=42)
dt.fit(train_scaled, train_target)
print(dt.score(train_scaled, train_target)) # 0.996921300750433
print(dt.score(test_scaled, test_target)) # 0.8592307692307692 # 과대적합

import matplotlib.pyplot as plt
from sklearn.tree import plot_tree
plt.figure(figsize=(10, 7))
plot_tree(dt, max_depth=1, filled=True, feature_names=['alcohol', 'sugar', 'pH'])
#plt.show()

dt = DecisionTreeClassifier(max_depth=3, random_state=42)
dt.fit(train_scaled, train_target)
print(dt.score(train_scaled, train_target)) # 0.8454877814123533
print(dt.score(test_scaled, test_target)) # 0.8415384615384616
#plt.figure(figsize=(20, 15))
#plot_tree(dt, max_depth=1, filled=True, feature_names=['alcohol', 'sugar', 'pH'])
#plt.show()

# 전처리
dt = DecisionTreeClassifier(max_depth=3,  random_state=42)
dt.fit(train_input, train_target)
print(dt.score(train_input, train_target)) # 0.8454877814123533
print(dt.score(test_input, test_target)) # 0.8415384615384616 # 위와 값이 동일
# 결론: 결정 트리에서는 전처리 과정이 필요하지 않다.


print(dt.feature_importances_) # 특성에 대한 중요도 출력
plt.figure(figsize=(20, 15))
plot_tree(dt, filled=True, feature_names=['alcohol', 'sugar', 'pH'])
plt.show()