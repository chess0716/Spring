import pandas as pd
from pymongo import MongoClient

# MongoDB에 연결
client = MongoClient('mongodb://localhost:27017/')
db = client['wine_database']  # 데이터베이스 이름
collection = db['wine_collection']  # 컬렉션 이름

# 와인 데이터 가져오기
wine_data = pd.read_csv('wine.csv')

# 와인 데이터를 MongoDB에 삽입
records = wine_data.to_dict(orient='records')
collection.insert_many(records)

print("와인 데이터가 MongoDB에 저장되었습니다.")
