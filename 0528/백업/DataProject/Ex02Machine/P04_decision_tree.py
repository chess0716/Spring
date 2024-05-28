
import pandas as pd
from pymongo import MongoClient
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.linear_model import LogisticRegression
import seaborn as sns
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc

# 폰트 설정
font_path = "C:\\Windows\\Fonts\\gulim.ttc"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)

# MongoDB 연결
client = MongoClient('mongodb://localhost:27017/')
db = client['wine_database']
collection = db['wine_collection']


cursor = collection.find({}, {'_id': 0})
wine_data = pd.DataFrame(list(cursor))

# 데이터 확인

# 입력 데이터와 타겟 데이터 설정
data = wine_data[['alcohol', 'sugar', 'pH']].to_numpy()
target = wine_data['class'].to_numpy()

train_input, test_input, train_target, test_target = train_test_split(
    data, target, random_state=42, test_size=0.2
)

ss = StandardScaler()
train_scaled = ss.fit_transform(train_input)
test_scaled = ss.transform(test_input)

# 결정 트리 모델 생성 및 훈련
tree = DecisionTreeClassifier(random_state=42)
tree.fit(train_scaled, train_target)

# 훈련 세트에서의 모델 정확도
train_accuracy = tree.score(train_scaled, train_target)
print("결정트리 훈련 세트 정확도: " ,train_accuracy)

# 테스트 세트에서의 모델 정확도
test_accuracy = tree.score(test_scaled, test_target)
print("결정트리 테스트 세트 정확도: ",test_accuracy)


# 예측 수행
predictions = tree.predict(test_scaled)

# 성능 평가 보고서 출력
print(classification_report(test_target, predictions))

# 혼동 행렬 출력
print("결정트리 혼동 행렬:")
print(confusion_matrix(test_target, predictions))

# 로지스틱 회귀 모델 생성 및 훈련
logreg = LogisticRegression(random_state=42)
logreg.fit(train_scaled, train_target)

# 훈련 세트에서의 모델 정확도
train_accuracy_logreg = logreg.score(train_scaled, train_target)
print(f"로지스틱 회귀 훈련 세트 정확도: {train_accuracy_logreg:.2f}")

# 테스트 세트에서의 모델 정확도
test_accuracy_logreg = logreg.score(test_scaled, test_target)
print(f"로지스틱 회귀 테스트 세트 정확도: {test_accuracy_logreg:.2f}")

# 예측 수행
predictions_logreg = logreg.predict(test_scaled)

# 성능 평가 보고서 출력
print("로지스틱 회귀 분류 보고서:")
print(classification_report(test_target, predictions_logreg))

# 혼동 행렬 출력
print("로지스틱 회귀 혼동 행렬:")
print(confusion_matrix(test_target, predictions_logreg))


plt.figure(figsize=(20,15))
plot_tree(tree,filled=True,feature_names=['alcohol','sugar','pH'])
plt.show()
# 혼동 행렬 히트맵
cm = confusion_matrix(test_target, predictions)
plt.figure(figsize=(8, 6))
sns.heatmap(cm, annot=False, fmt='d', cmap='Blues')
for i in range(cm.shape[0]):
    for j in range(cm.shape[1]):
        plt.text(j + 0.5, i + 0.5, str(cm[i, j]),
                 fontdict=dict(ha='center', va='center', color='black', fontsize=13))
plt.xlabel('예측 레이블')
plt.ylabel('실제 레이블')
plt.title('결정 트리 혼동 행렬')
plt.show()

# 특성 중요도
feature_importances = tree.feature_importances_
features = ['알코올', '설탕', 'pH']

# 중요도 그래프
plt.figure(figsize=(10, 6))
sns.barplot(x=features, y=feature_importances)
plt.title('결정 트리 특성 중요도')
plt.xlabel('특성')
plt.ylabel('중요도')
plt.show()

