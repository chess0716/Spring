import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from pymongo import MongoClient
from matplotlib import font_manager, rc
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import StandardScaler

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

perch_length = perch_data['Length2'].values.reshape(-1, 1)
perch_weight = perch_data['Weight'].values.reshape(-1, 1)
perch_width = perch_data['Width'].values.reshape(-1, 1)

perch_data = perch_length + perch_weight + perch_width

# 데이터 분할
train_input, test_input, train_target, test_target = train_test_split(
    perch_length, perch_weight, random_state=42
)
poly = PolynomialFeatures(include_bias=False)
# poly.fit([[2,3]]); print(poly.transform([[2,3]]))
poly.fit(train_input)
train_poly = poly.transform(train_input)
test_poly = poly.transform(test_input)

lr = LinearRegression()
lr.fit(train_poly, train_target)
print(lr.score(train_poly, train_target))
print(lr.score(test_poly, test_target))
#
# poly = PolynomialFeatures(degree=5 , include_bias=False)
# poly.fit(train_input)
# train_poly = poly.transform(train_input)
# test_poly = poly.transform(test_input)
# print(train_poly.shape)
#
# lr.fit(train_poly,train_target)
# print(lr.score(train_poly, train_target))
# print(lr.score(test_poly, test_target))
# 훈련세트에 대하여 결론 속성을 늘릴수록 완벽하게 학습을 할 수 있으나
# 테스트세트에서 형편없는 점수를 받기에 훈련세트에 대하여 너무 너무 과댖거합 되었다고 본다

ss = StandardScaler()  # 평균과 표준편차를 직접 구해 특성을 표준점수로 변경
ss.fit(train_poly)
train_scaled = ss.transform(train_poly)
test_scaled = ss.transform(test_poly)

# 규제 추가를 위한 2가지 모듈 : Ridge , Lasso

# Ridge 모델 적용
from sklearn.linear_model import Ridge

ridge = Ridge()
ridge.fit(train_scaled, train_target)
print(ridge.score(train_scaled, train_target))
print(ridge.score(test_scaled, test_target))
# 비교적 좋은 성능

# 릿지,라쏘 사용할 때 규제의 양을 임의로 조절 하기위해 alpha 매개변수 설정하기
train_score = []
test_score = []
alpha_list = [0.001, 0.01, 0.1, 1, 10, 100]
for alpha in alpha_list:
    ridge = Ridge(alpha=alpha)
    ridge.fit(train_scaled, train_target)
    train_score.append(ridge.score(train_scaled, train_target))
    test_score.append(ridge.score(test_scaled, test_target))
print(alpha_list)

plt.plot(np.log10(alpha_list), train_score, label='Train Score')
plt.plot(np.log10(alpha_list), test_score, label='Test Score')
plt.xlabel('log10(Alpha)')
plt.ylabel('Score')
plt.title('Ridge: Train Score vs Test Score')
plt.legend()
plt.show()

ridge = Ridge(alpha=10)
ridge.fit(train_scaled, train_target)
print(ridge.score(train_scaled, train_target))
print(ridge.score(test_scaled, test_target))
