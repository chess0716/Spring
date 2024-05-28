import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from matplotlib import font_manager, rc
from pymongo import MongoClient
from sklearn.model_selection import  train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
from sklearn.linear_model import LogisticRegression
from scipy.special import expit
# 폰트 설정
font_path = "C:\\Windows\\Fonts\\gulim.ttc"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)

# MongoDB 연결
client = MongoClient('mongodb://localhost:27017/')
db = client['fish_database']
collection = db['fish_collection']

# MongoDB에서 데이터 로드
cursor = collection.find({}, {'_id': 0, 'Species': 1, 'Weight': 1, 'Length1': 1, 'Length2': 1, 'Length3': 1, 'Height': 1, 'Width': 1})
fish_data = pd.DataFrame(list(cursor))

# 필요없는 '_id' 컬럼 제거
# if '_id' in fish_data.columns:
#     fish_data.drop('_id', axis=1, inplace=True)
fish_data.rename(columns={'Length2': 'Length', 'Length3': 'Diagonal'}, inplace=True)
fish_data.drop(columns=['Length1'], inplace=True)
print(fish_data)

# 입력 데이터와 타겟 데이터 설정
fish_input = fish_data[['Weight', 'Length', 'Diagonal', 'Height', 'Width']].to_numpy()
fish_target = fish_data['Species'].to_numpy()

# 데이터 분할
train_input, test_input, train_target, test_target = train_test_split(
    fish_input, fish_target, random_state=42
)
# 전처리 과정 : 근접하였을 대 가장 가까운 데이터 매칭하기 위함 매칭 표준화, 평균 0, 분산을 1로 조정
ss = StandardScaler()
ss.fit(train_input)
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(train_scaled,train_target)

# 테스트 데이터에 대한 예측
test_pred = knn.predict(test_scaled)

accuracy = knn.score(test_scaled, test_target)
print("테스트 세트 정확도:", accuracy)
print(knn.classes_);print(knn.predict(test_scaled[:5]))
proba = knn.predict_proba(test_scaled[:5])
print(np.round(proba,decimals=4))

# -5부터 5까지 0.1 간격으로 z 값을 생성합니다.
z = np.arange(-5, 5, 0.1)
# 로지스틱 함수에 z 값을 적용하여 확률값을 계산합니다.
# 로지스틱 함수: P(Negative) = 1 / (1 + exp(-z))
pni = 1 / (1 + np.exp(-z))
# z 값에 따른 확률값을 그래프로 그립니다.
# x 축은 z 값, y 축은 확률값(P(Negative))입니다.
plt.plot(z, pni)
plt.xlabel('z')
plt.ylabel('P(Negative)')
plt.title('Logistic Function')
plt.grid(True)
plt.show()

#로지스틱 회귀 분류를 2진분류부터 수행하기
char_arr = np.array(['A','B','C','D','E'])
# 불리언 인덱싱을 사용하여 배열의 일부를 선택합니다.
selected_elements = char_arr[[True, False, True, False, False]]
print(selected_elements)

# 불리언 인덱싱을 사용하여 train 데이터에서 Bream과 Smelt 종만 선택합니다.
bream_smelt_indexes = (train_target == 'Bream') | (train_target == 'Smelt')
train_bream_smelt = train_scaled[bream_smelt_indexes]
target_bream_smelt = train_target[bream_smelt_indexes]
print(train_bream_smelt)
print(target_bream_smelt)
lr = LogisticRegression()
lr.fit(train_bream_smelt,target_bream_smelt)
print(lr.predict(train_bream_smelt[:5]))
print(lr.predict_proba(train_bream_smelt[:5]))
print(lr.classes_);print(lr.coef_,lr.intercept_)

decisions = lr.decision_function(train_bream_smelt[:5])
print(decisions) # 처음 5개의 행에 대한 방정식을 족용한 값을 출력
print(expit(decisions)) # 파이썬의 로지스틱 함수를 이용하여 출력된 값을 변환

# 로지스틱 회귀로 다중분류 수행하기
# 7개의 생선으로 분류
lr = LogisticRegression(C=20,max_iter=1000)
lr.fit(train_scaled,train_target)
print(lr.score(train_scaled,train_target))
print(lr.score(test_scaled,test_target))
print(lr.predict(test_scaled[:5]))
proba = lr.predict_proba(test_scaled[:5])
print(np.round(proba,decimals=3));print(lr.classes_);
# 다중분류 함수를 softmax 함수를 적용
print(lr.coef_,lr.intercept_.shape)

from scipy.special import softmax
proba = softmax(decisions,axis=1)
print(np.round(proba,decimals=3))