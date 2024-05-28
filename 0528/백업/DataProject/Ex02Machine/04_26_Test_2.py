import matplotlib.pyplot as plt
from sklearn.linear_model import SGDClassifier
from sklearn.metrics import accuracy_score
import pandas as pd
from pymongo import MongoClient
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler

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

# 데이터 분할
train_input, test_input, train_target, test_target = train_test_split(
    fish_input, fish_target, random_state=42
)

# 데이터 표준화
scaler = StandardScaler()
scaler.fit(train_input)
train_scaled = scaler.transform(train_input)
test_scaled = scaler.transform(test_input)

# 시도할 하이퍼파라미터 값 목록
alpha_values = [0.0001, 0.001, 0.01, 0.1, 1, 10]
max_iter_values = [100, 200, 300]

# 결과를 저장할 리스트 초기화
results = []
#6번
for alpha in alpha_values:
    for max_iter in max_iter_values:
        # SGDClassifier 모델 초기화
        sgd_clf = SGDClassifier(loss='log_loss', random_state=42, alpha=alpha, max_iter=max_iter)

        # 모델 훈련
        sgd_clf.fit(train_scaled, train_target)

        # 훈련 및 테스트 세트에서 정확도 측정
        train_accuracy = accuracy_score(train_target, sgd_clf.predict(train_scaled))
        test_accuracy = accuracy_score(test_target, sgd_clf.predict(test_scaled))

        # 결과 저장
        results.append((alpha, max_iter, train_accuracy, test_accuracy))

# 결과를 DataFrame으로 변환
results_df = pd.DataFrame(results, columns=['Alpha', 'Max_iter', 'Train_accuracy', 'Test_accuracy'])
print("6번) 훈련세트와 테스트 세트의 정확도:")
print(train_accuracy)
print(test_accuracy)
# 결과 출력
print(results_df)

# 가장 높은 테스트 세트 정확도를 가진 모델 선택
best_model = results_df.iloc[results_df['Test_accuracy'].idxmax()]
print("10번) 가장 높은 테스트 세트 정확도를 가진 모델:")
print(best_model)

# 선택된 모델의 하이퍼파라미터와 정확도 출력
best_alpha = best_model['Alpha']
best_max_iter = best_model['Max_iter']
best_train_accuracy = best_model['Train_accuracy']
best_test_accuracy = best_model['Test_accuracy']

print("선택된 모델의 하이퍼파라미터:")
print("Alpha:", best_alpha)
print("Max_iter:", best_max_iter)
print("선택된 모델의 훈련 세트 정확도:", best_train_accuracy)
print("선택된 모델의 테스트 세트 정확도:", best_test_accuracy)
