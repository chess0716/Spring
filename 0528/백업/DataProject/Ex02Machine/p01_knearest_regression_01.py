import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.neighbors import KNeighborsRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error
from pymongo import MongoClient
from matplotlib import font_manager, rc

font_path = "C:\\Windows\\Fonts\\gulim.ttc"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)

# MongoDB 클라이언트 설정
client = MongoClient('mongodb://localhost:27017/')
db = client['fish_database']
collection = db['fish_collection']

# MongoDB에서 데이터 로드
cursor = collection.find({'Species': 'Perch'})  # 'Perch' 종만 필터링
perch_data = pd.DataFrame(list(cursor))

# 필요없는 '_id' 컬럼 제거
if '_id' in perch_data.columns:
    perch_data.drop('_id', axis=1, inplace=True)

perch_length = perch_data['Length2'].values.reshape(-1, 1)
perch_weight = perch_data['Weight'].values

# 데이터 분할
train_input, test_input, train_target, test_target = train_test_split(perch_length, perch_weight, random_state=42)

# 모델 생성 및 학습
knn = KNeighborsRegressor(n_neighbors=5)
knn.fit(train_input, train_target)

# 평가
test_score = knn.score(test_input, test_target)
test_prediction = knn.predict(test_input)
mae = mean_absolute_error(test_target, test_prediction)
train_score = knn.score(train_input, train_target)

# 새로운 길이 데이터 예측
new_lengths = np.array([[25], [30], [35]])
predicted_weights = knn.predict(new_lengths)

# 예측 결과 출력
# 결론 : 훈련셋 > 테스트셋 => 과대 적합
# 결론 : 훈련셋 < 테스트셋 => 과소 적합
# 최종결론: 과대 적합일 경우 모델을 덜 복잡하게 만들어야 한다. 근접 갯수를 늘림
# 과소 적합일 경우 모델을 더 복잡하게 만들어야 한다. 근접갯수를 줄임
print(f"테스트 세트 R^2 점수: {test_score}")
print(f"평균 절대 오차: {mae}")
print(f"훈련 세트 R^2 점수: {train_score}")
print("예측된 무게:")
for length, weight in zip(new_lengths, predicted_weights):
    print(f"길이 {length[0]} cm 농어의 예상 무게: {weight:.2f} g")

# 시각화
predict_range = np.arange(perch_length.min(), perch_length.max(), 0.1).reshape(-1, 1)
predict_range_weight = knn.predict(predict_range)

plt.figure(figsize=(10, 6))
plt.scatter(perch_length, perch_weight, color='blue', label='실제 무게')
plt.plot(predict_range, predict_range_weight, color='red', label='예측 무게')
plt.scatter(new_lengths, predicted_weights, color='green', marker='^', label='새로운 예측')
plt.xlabel('길이 (cm)')
plt.ylabel('무게 (g)')
plt.title('농어 길이에 따른 무게 예측')
plt.legend()
plt.show()

