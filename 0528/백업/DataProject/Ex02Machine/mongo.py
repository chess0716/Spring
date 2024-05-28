import pandas as pd
from pymongo import MongoClient

# MongoDB 클라이언트 설정
client = MongoClient('mongodb://localhost:27017/')
db = client['fish_database']  # 데이터베이스 이름
collection = db['fish_collection']  # 컬렉션 이름

# CSV 파일 읽기
data = pd.read_csv('Fish.csv')

# 데이터프레임을 딕셔너리 리스트로 변환
data_dict = data.to_dict('records')

# MongoDB에 데이터 삽입
collection.insert_many(data_dict)
