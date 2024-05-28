import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.neighbors import KNeighborsRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error
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

# K 최근접 이웃 회귀 모델 생성 및 훈련
knn = KNeighborsRegressor(n_neighbors=3)
knn.fit(train_input, train_target)

# 선형 회귀 모델 생성 및 훈련
lr = LinearRegression()
lr.fit(train_input, train_target)

# 선형 회귀 및 KNN 모델의 추세선을 그리기 위한 입력 범위
x_range = np.linspace(min(train_input), max(train_input), 100).reshape(-1, 1)
predicted_weights_knn = knn.predict(x_range)
predicted_weights_lr = lr.predict(x_range)

# 50cm 농어에 대한 무게 예측
predicted_weight_knn = knn.predict([[50]])[0]
predicted_weight_lr = lr.predict([[50]])[0]



# 모델 평가
knn_predicted_weight = knn.predict(test_input)
knn_mae = mean_absolute_error(test_target, knn_predicted_weight)
print("KNN MAE:", knn_mae)

lr_predicted_weight = lr.predict(test_input)
lr_mae = mean_absolute_error(test_target, lr_predicted_weight)
print("Linear Regression MAE:", lr_mae)

# KNN 모델의 결정 계수 출력
knn_score = knn.score(test_input, test_target)
print("KNN R^2 Score:", knn_score)

# 선형 회귀 모델의 결정 계수 출력
lr_score = lr.score(test_input, test_target)
print("Linear Regression R^2 Score:", lr_score)

# 산점도 그래프 그리기
plt.figure(figsize=(10, 6))
plt.scatter(train_input, train_target, color='blue', label='Train data')
plt.scatter(test_input, test_target, color='red', label='Test data')
plt.plot(x_range, predicted_weights_knn, linestyle='--', color='purple', label='KNN Trend')
plt.plot(x_range, predicted_weights_lr, linestyle='-', color='orange', label='Linear Regression Trend')
plt.scatter(50, predicted_weight_knn, marker="^", color='purple', label='KNN Prediction')
plt.scatter(50, predicted_weight_lr, marker="o", color='orange', label='Linear Regression Prediction')
plt.title('Perch Length vs Weight')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.show()