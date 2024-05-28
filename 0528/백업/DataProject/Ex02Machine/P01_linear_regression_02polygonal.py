import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from pymongo import MongoClient
from matplotlib import font_manager, rc

# 폰트 설정
font_path = "C:\\Windows\\Fonts\\gulim.ttc"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)

# MongoDB 연결
client = MongoClient('mongodb://localhost:27017/')
db = client['fish_database']
collection = db['fish_collection']

# MongoDB에서 데이터 로드
cursor = collection.find({'Species': 'Perch'})
perch_data = pd.DataFrame(list(cursor))

# 필요없는 '_id' 컬럼 제거
if '_id' in perch_data.columns:
    perch_data.drop('_id', axis=1, inplace=True)

# 길이와 무게 데이터 추출
perch_length = perch_data['Length2'].values.reshape(-1, 1)
perch_weight = perch_data['Weight'].values

# 데이터 분할
train_input, test_input, train_target, test_target = train_test_split(
    perch_length, perch_weight, random_state=42
)

# 다항 특성 추가
train_poly = np.column_stack((train_input ** 2, train_input))
test_poly = np.column_stack((test_input ** 2, test_input))

# 선형 회귀 모델 훈련
lr = LinearRegression()
lr.fit(train_poly, train_target)

# 길이가 50cm인 농어의 무게 예측
predicted_weight = lr.predict([[50 ** 2, 50]])

# 회귀 모델의 계수와 절편 출력
print("Coefficients:", lr.coef_)
print("Intercept:", lr.intercept_)

# 훈련 데이터의 산점도 및 회귀 직선 그리기
point = np.arange(15, 50)
plt.figure(figsize=(10, 6))
plt.scatter(train_input, train_target, label='Training data')
plt.plot(point, lr.coef_[0] * point ** 2 + lr.coef_[1] * point + lr.intercept_, color='red', label='Regression line')
plt.scatter(50, predicted_weight, marker='^', color='green', label='예측 무게 (50cm)')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.title('농어 길이 별 무게 예측')
plt.legend()
plt.show()

# 훈련 및 테스트 데이터에 대한 회귀 모델의 결정 계수 출력
print("Training R^2 Score:", lr.score(train_poly, train_target))
print("Testing R^2 Score:", lr.score(test_poly, test_target))
