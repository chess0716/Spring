from pymongo import MongoClient
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import LabelEncoder

# MongoDB Connection
client = MongoClient('mongodb://localhost:27017/')
db = client['gram_database']
collection = db['gram_collection']

# Load Data
data = list(collection.find())
df = pd.DataFrame(data)

# Rename Columns
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

# Remove Outliers
def remove_outliers(df, column):
    # Replace string patterns with empty string
    df['budget'] = df['budget'].str.replace('원 미만', '').str.replace('원 초과', '').str.replace('원 이상', '').str.replace('무료', '0').str.replace(',', '')

    # Convert to numeric, errors='coerce' will coerce non-numeric values to NaN
    df['budget'] = pd.to_numeric(df['budget'], errors='coerce')

    # Drop rows with NaN values
    df = df.dropna(subset=['budget'])

    q1 = df[column].quantile(0.25)
    q3 = df[column].quantile(0.75)
    iqr = q3 - q1
    lower_bound = q1 - 1.5 * iqr
    upper_bound = q3 + 1.5 * iqr
    return df[(df[column] >= lower_bound) & (df[column] <= upper_bound)]

# Remove outliers
df = remove_outliers(df, 'budget')

# Group Age
age_groups = ['10대', '20대', '30대', '40대', '50대']
for age_group in age_groups:
    df[f'age_{age_group}'] = (df['age'] == age_group).astype(int)

# One-Hot Encoding
df = pd.get_dummies(df, columns=['age'] + ['age_' + age_group for age_group in age_groups] + ['environment'])

# New Feature Creation
df['budget_per_frequency'] = df['budget'] / (df['frequency'].str.replace('일', '').astype(int) + 1)

# Setup Label Encoders
label_encoders = {}
for column in ['gender', 'time_preference']:
    le = LabelEncoder()
    df[column] = le.fit_transform(df[column])
    label_encoders[column] = le  # Store encoders

# Feature Selection
sports = ['아이스링크', '볼더링', '양궁', '사격', '스쿼시', '플라잉요가', '바둑', '체스', '홀덤',
          '트릭킹', '폴댄스', '실내서바이벌', '스쿠버다이빙', '프리다이빙', '스노클링', '비치 발리볼',
          '서핑', '게이트볼', '승마', '패러글라이딩', '카누', '카약', '산악자전거', '스케이트보드']
features = df[['gender', 'time_preference'] + sports]
labels = df[sports]

# Data Split
X_train, X_test, y_train, y_test = train_test_split(features, labels, test_size=0.2, random_state=42)

# RandomForest Model
model = RandomForestClassifier(n_estimators=50, max_depth=15, min_samples_leaf=1, random_state=42)
model.fit(X_train, y_train)

# Prepare New Data
new_data = pd.DataFrame({
    'timestamp': ['2024. 5. 2 오후 12:06:02'],
    'gender': ['여자'],
    'age': ['20대'],
    'environment': ['실내'],
    'frequency': ['주 1-2일'],
    'budget': ['100000원 미만'],
    'time_preference': ['주말'],
    'interests': ["승마, 실내서바이벌, 산악자전거, 아이스링크"]
})

# Remove Outliers for new data
new_data['budget'] = new_data['budget'].apply(lambda x: 100000 if '100000' in x else 0)  # Adjust for outlier removal

# One-Hot Encoding for new data
new_data = pd.get_dummies(new_data, columns=['age'] + ['environment'])

# New Feature Creation for new data
new_data['budget_per_frequency'] = new_data['budget'] / (new_data['frequency'].str.replace('일', '').astype(int) + 1)

# Label Encoding for new data
for column in ['gender', 'time_preference']:
    new_data[column] = label_encoders[column].transform(new_data[column])

# Select Features for new data
new_features = new_data[['gender', 'time_preference'] + sports]

# Predict New Data with Probability
prob_predictions = model.predict_proba(new_features)
predicted_sports = []

for i, sport in enumerate(sports):
    # Extract probability of 'Preferred' class from probability array
    if prob_predictions[i].shape[1] == 1:
        sport_prob = prob_predictions[i][0][0] if prob_predictions[i][0][0] > 0.5 else 0
    else:
        sport_prob = prob_predictions[i][0][1]

    if sport_prob >= 0.3:  # Set probability threshold
        predicted_sports.append(sport)

print("새로운 데이터에 대한 선호 스포츠 예측: ", predicted_sports)
