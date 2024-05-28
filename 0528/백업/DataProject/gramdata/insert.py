import pandas as pd
from pymongo import MongoClient

# MongoDB 연결 설정
client = MongoClient('localhost', 27017)
db = client['gram_database']  # 여기에는 실제 사용하는 데이터베이스 이름을 입력하세요
collection = db['sport_collection']  # 여기에는 실제 사용하는 컬렉션 이름을 입력하세요

# CSV 파일 읽기
df = pd.read_csv('gram.csv')


data = df.to_dict(orient='records')
collection.insert_many(data)

print("데이터베이스에 성공적으로 삽입되었습니다.")
