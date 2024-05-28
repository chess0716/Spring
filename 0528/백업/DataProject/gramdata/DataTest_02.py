from pymongo import MongoClient
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import LabelEncoder

# MongoDB Connection
client = MongoClient('mongodb://localhost:27017/')
db = client['gram_database']
collection = db['gram_collection']

# Load Data from MongoDB
data = list(collection.find({}, {'_id': 0}))
df = pd.DataFrame(data)

# Rename Columns to more readable names
df.rename(columns={
    '타임스탬프': 'timestamp',
    '성별': 'gender',
    '나이': 'age',
    '선호 장소': 'environment',
    '주간 운동량': 'frequency',
    '운동 가용 금액': 'budget',
    '운동 시간': 'time_preference',
    '선호 종목(복수선택 가능)': 'interests'
}, inplace=True)

# Transform 'interests' into individual sport columns with binary values
sports = ['아이스링크', '볼더링', '양궁', '사격', '스쿼시', '플라잉요가', '바둑', '체스', '홀덤',
          '트릭킹', '폴댄스', '실내서바이벌', '스쿠버다이빙', '프리다이빙', '스노클링', '비치 발리볼',
          '서핑', '게이트볼', '승마', '패러글라이딩', '카누', '카약', '산악자전거', '스케이트보드']
for sport in sports:
    df[sport] = df['interests'].apply(lambda x: 1 if sport in x else 0)

# Define sports features data
sports_features = pd.DataFrame({
    'Sport': sports,
    'Environment': [3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],  # 실내: 3, 실외: 1
    'ActivityType': [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 2, 2, 1],  # 개인: 1, 팀: 2
    'ActivityIntensity': [3, 3, 2, 2, 3, 1, 1, 1, 1, 3, 2, 2, 3, 3, 2, 2, 3, 1, 2, 2, 2, 2, 3, 3],  # 하: 1, 중: 2, 상: 3
    'SkillLevel': [1, 2, 2, 2, 3, 2, 3, 2, 2, 3, 3, 2, 3, 3, 2, 1, 3, 1, 2, 3, 2, 2, 3, 2],  # 초급: 1, 중급: 2, 고급: 3
    'Accessibility': [3, 3, 1, 3, 2, 2, 1, 1, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1, 2, 1, 1, 1, 2, 2],  # 높음: 3, 중간: 2, 낮음: 1
    'Popularity': [3, 3, 2, 3, 2, 2, 1, 1, 3, 2, 3, 3, 2, 2, 3, 3, 3, 1, 1, 2, 2, 2, 1, 2],  # 높음: 3, 보통: 2, 낮음: 1
    'RiskLevel': [2, 3, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 3, 3, 2, 1, 3, 1, 2, 2, 2, 2, 3, 3]  # 낮음: 1, 중간: 2, 높음: 3
})

# Define sports features data
# Define sports features data
sports_features_df = pd.DataFrame(index=df.index)

# 열 추가를 한 번에 처리
data_to_concat = []
for index, row in sports_features.iterrows():
    sport = row['Sport']
    sport_data = {}
    for feature in sports_features.columns[1:]:
        sport_data[f"{sport}_{feature}"] = df[sport] * row[feature]
    data_to_concat.append(pd.DataFrame(sport_data))

# DataFrame 병합
df = pd.concat([df] + data_to_concat, axis=1)

# Encode categorical data and prepare features
label_encoders = {}
for column in ['gender', 'age', 'environment', 'frequency', 'budget', 'time_preference']:
    le = LabelEncoder()
    df[column] = le.fit_transform(df[column])
    label_encoders[column] = le

# Define the features and labels for machine learning
features_columns = [col for col in df.columns if col not in ['timestamp', 'interests', 'gender', 'age', 'environment', 'frequency', 'budget', 'time_preference'] + sports]
X = df[features_columns]
y = df['interests']  # You will need to adjust this based on your specific needs

# Split data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train a RandomForestClassifier
model = RandomForestClassifier(n_estimators=50, max_depth=15, min_samples_leaf=1, random_state=42)
model.fit(X_train, y_train)

# Evaluate the model
predictions = model.predict(X_test)
accuracy = accuracy_score(y_test, predictions)
print(f"Model Accuracy: {accuracy:.2%}")

# Prediction for new_data_1
new_data_1 = pd.DataFrame({
    'timestamp': ['2024. 5. 2 PM 12:06:02'],  # Corrected timestamp format
    'gender': ['여자'],
    'age': ['20대'],
    'environment': ['실내'],
    'frequency': ['주 1-2일'],
    'budget': ['100000원 미만'],
    'time_preference': ['주말'],
    'interests': ["승마, 실내서바이벌, 산악자전거, 아이스링크"]
})

# Rename columns for consistency
new_data_1.rename(columns={
    '성별': 'gender',
    '나이': 'age',
    '선호 장소': 'environment',
    '주간 운동량': 'frequency',
    '운동 가용 금액': 'budget',
    '운동 시간': 'time_preference'
}, inplace=True)

# Transform 'interests' into individual sport columns with binary values
for sport in sports:
    new_data_1[sport] = new_data_1['interests'].apply(lambda x: 1 if sport in x else 0)

# Encode categorical data
for column in ['gender', 'age', 'environment', 'frequency', 'budget', 'time_preference']:
    le = label_encoders[column]
    new_data_1[column] = le.transform(new_data_1[column])

# Define the features for new_data_1
new_data_1_features = [col for col in new_data_1.columns if col not in ['timestamp', 'interests']]

# Make predictions for new_data_1
predictions_new_data_1 = model.predict_proba(new_data_1[new_data_1_features])

# Print predicted interests for new_data_1
predicted_sports_new_data_1 = []
for idx, prob in enumerate(predictions_new_data_1[0]):
    if prob > 0.5:  # If probability is greater than 0.5, consider it as interest
        predicted_sports_new_data_1.append(sports[idx])

print("Predicted interests for new_data_1:", predicted_sports_new_data_1)
