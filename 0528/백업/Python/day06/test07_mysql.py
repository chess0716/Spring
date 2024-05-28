import pymysql

# MySQL 연결 정보 입력
host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'naverdb'
charset = 'utf8mb4'

# MySQL 연결
con = pymysql.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()

# 테이블 생성 쿼리
# create_table_query = '''
# CREATE TABLE IF NOT EXISTS userTable (
#     id CHAR(4),
#     userName CHAR(15),
#     email CHAR(20),
#     birthYear INT
# )
# '''

# # 사용자 테이블 생성
# cur.execute(create_table_query)

# 사용자 정보 삽입 쿼리
insert_data_query = "INSERT INTO userTable VALUES ('john', 'John Bann', 'john@naver.com', 1990)"

# 사용자 정보 삽입
cur.execute(insert_data_query)

# 변경 사항 커밋
con.commit()

# 연결 종료
con.close()