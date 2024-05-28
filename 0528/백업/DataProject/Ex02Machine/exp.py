import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
import numpy as np

# 가상의 사용자 데이터 생성
data = {
    'Age': [25, 32, 47, 51, 23, 36, 28, 43, 39, 22],
    'Favorite_Sport': ['Basketball', 'Tennis', 'Swimming', 'Basketball', 'Running', 'Swimming', 'Tennis', 'Running', 'Basketball', 'Swimming'],
    'Previous_Participation': [1, 3, 2, 0, 1, 2, 0, 1, 1, 4],
    'Will_Participate': [1, 1, 0, 0, 1, 1, 0, 0, 1, 1]  # 1 for Yes, 0 for No
}

# 데이터 프레임 생성
df = pd.DataFrame(data)

# 'Favorite_Sport'를 숫자형으로 변환
df['Favorite_Sport'] = df['Favorite_Sport'].astype('category').cat.codes

# 특성과 레이블 분리
X = df[['Age', 'Favorite_Sport', 'Previous_Participation']]
y = df['Will_Participate']

# 데이터를 훈련 세트와 테스트 세트로 분할
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42, stratify=y)
# 랜덤 포레스트 분류기 모델 훈련
model = RandomForestClassifier(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

# 테스트 세트에 대한 예측
predictions = model.predict(X_test)

# 정확도 계산
accuracy = accuracy_score(y_test, predictions)
print(f'Accuracy: {accuracy:.2f}')

# 새로운 사용자 데이터로 예측
new_user_df = pd.DataFrame([[30, 1, 2]], columns=['Age', 'Favorite_Sport', 'Previous_Participation'])

prediction = model.predict(new_user_df)
print('New user will participate:' if prediction[0] == 1 else 'New user will not participate')
