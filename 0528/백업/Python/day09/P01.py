from sklearn.neighbors import KNeighborsClassifier

# 데이터 준비
X_train = [[0, 0], [1, 1], [2, 2]]  # 특성 데이터
y_train = [0, 1, 1]                 # 레이블 데이터

# 모델 생성
knn = KNeighborsClassifier(n_neighbors=2)

# 모델 학습
knn.fit(X_train, y_train)

# 예측
print(knn.predict([[1.5, 1.5]]))  # 출력: [1]
