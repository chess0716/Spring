import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import StandardScaler

# 데이터 불러오기
data = pd.read_csv('Fish.csv')

# 종류별로 데이터 분리
fish_species = data['Species'].unique()

# 종류별 산점도 그리기
plt.figure(figsize=(12, 8))
for species in fish_species:
    species_data = data[data['Species'] == species]
    plt.scatter(species_data['Length2'], species_data['Weight'], label=species)

plt.title('Fish Length vs Weight by Species')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.grid(True)
plt.show()

# 머신러닝 모델 훈련을 위한 데이터 준비
X = data[['Length2', 'Weight']]
y = data['Species']

# 데이터 분할
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 표준화
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

# KNN 분류기 모델 훈련 및 평가
def train_and_evaluate_knn(X_train, y_train, X_test, y_test, metric='euclidean'):
    knn = KNeighborsClassifier(n_neighbors=5, metric=metric)
    knn.fit(X_train, y_train)
    y_pred = knn.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)
    return accuracy, y_pred

# 유클리드 거리 사용
accuracy_euclidean, y_pred_euclidean = train_and_evaluate_knn(X_train_scaled, y_train, X_test_scaled, y_test)

# 맨해튼 거리 사용
accuracy_manhattan, y_pred_manhattan = train_and_evaluate_knn(X_train_scaled, y_train, X_test_scaled, y_test, metric='manhattan')

print("유클리드 거리 사용 정확도:", accuracy_euclidean)
print("맨해튼 거리 사용 정확도:", accuracy_manhattan)

# 유클리드 거리와 맨해튼 거리에 대한 산점도 그래프 그리기
plt.figure(figsize=(12, 8))

# 유클리드 거리
plt.subplot(1, 2, 1)
for species in fish_species:
    species_data = X_test_scaled[y_pred_euclidean == species]
    plt.scatter(species_data[:, 0], species_data[:, 1], label=species, alpha=0.5)
plt.title('Predicted Fish Length vs Weight by Species (Euclidean)')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.grid(True)

# 맨해튼 거리
plt.subplot(1, 2, 2)
for species in fish_species:
    species_data = X_test_scaled[y_pred_manhattan == species]
    plt.scatter(species_data[:, 0], species_data[:, 1], label=species, alpha=0.5)
plt.title('Predicted Fish Length vs Weight by Species (Manhattan)')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.grid(True)

plt.tight_layout()
plt.show()

# K 값에 따른 정확도 확인
for k in range(1, 20):
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X_train_scaled, y_train)
    y_pred = knn.predict(X_test_scaled)
    accuracy = accuracy_score(y_test, y_pred)
    print(f"K={k}, 정확도: {accuracy:.3f}")

# K=2일 때의 모델 훈련 및 예측
knn = KNeighborsClassifier(n_neighbors=2)
knn.fit(X_train_scaled, y_train)
y_pred = knn.predict(X_test_scaled)

# 예측 결과를 사용하여 산점도 그래프 작성
plt.figure(figsize=(12, 8))
for species in fish_species:
    species_data = X_test_scaled[y_pred == species]
    plt.scatter(species_data[:, 0], species_data[:, 1], label=species, alpha=0.5)

plt.title('Predicted Fish Length vs Weight by Species (K=2)')
plt.xlabel('Length2 (cm)')
plt.ylabel('Weight (g)')
plt.legend()
plt.grid(True)
plt.show()