import matplotlib.pyplot as plt
from sklearn.linear_model import SGDClassifier
from sklearn.metrics import accuracy_score
import pandas as pd
from pymongo import MongoClient
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
import numpy as np
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
alpha_values = [ 0.001, 0.01, 0.1, 1]
max_iter_values = [ 300]

# 결과를 저장할 리스트 초기화
results = []

# 클래스 레이블이 필요하므로 numpy의 unique 함수 사용
classes = np.unique(train_target)

# 에포크마다 훈련 및 테스트 세트의 정확도 기록
n_epochs = 300
train_accuracies = np.zeros((len(alpha_values), len(max_iter_values), n_epochs))
test_accuracies = np.zeros((len(alpha_values), len(max_iter_values), n_epochs))

# 하이퍼파라미터 조합별로 반복 훈련 및 정확도 기록
for alpha_idx, alpha in enumerate(alpha_values):
    for max_iter_idx, max_iter in enumerate(max_iter_values):
        # SGDClassifier 모델 초기화
        sgd_clf = SGDClassifier(loss='log_loss', random_state=42, alpha=alpha, max_iter=max_iter)

        for epoch in range(n_epochs):
            # partial_fit을 사용하여 데이터를 점진적으로 훈련
            sgd_clf.partial_fit(train_scaled, train_target, classes=classes)

            # 훈련 및 테스트 세트의 정확도 측정 및 기록
            train_accuracy = accuracy_score(train_target, sgd_clf.predict(train_scaled))
            test_accuracy = accuracy_score(test_target, sgd_clf.predict(test_scaled))
            train_accuracies[alpha_idx, max_iter_idx, epoch] = train_accuracy
            test_accuracies[alpha_idx, max_iter_idx, epoch] = test_accuracy

# 누적 결과 시각화
plt.figure(figsize=(12, 8))
for alpha_idx, alpha in enumerate(alpha_values):
    for max_iter_idx, max_iter in enumerate(max_iter_values):
        # 평균을 계산하는 대신 각 에포크의 정확도 배열을 직접 사용
        plt.plot(range(n_epochs), train_accuracies[alpha_idx, max_iter_idx, :], label=f'Train Acc (alpha={alpha}, max_iter={max_iter})')
        plt.plot(range(n_epochs), test_accuracies[alpha_idx, max_iter_idx, :], label=f'Test Acc (alpha={alpha}, max_iter={max_iter})', linestyle='--')

plt.title('"다양한 하이퍼파라미터에 대한 에포크별 누적 정확도')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
