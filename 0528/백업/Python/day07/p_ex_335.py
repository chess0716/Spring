import pymysql
import matplotlib.pyplot as plt

inStr = '''죽는 날까지 하늘을 우러러 한 점 부끄럼이 없기를,
잎새에 이는 바람에도 나는 괴로워했다.
별을 노래하는 마음으로 모든 죽어가는 것을 사랑해야지.
그리고 나한테 주어진 길을 걸어가야겠다.
오늘 밤에도 별이 바람에 스치운다. '''

con, cur = None, None
host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'

con = pymysql.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()

# 테이블 생성 쿼리
cur.execute("CREATE TABLE IF NOT EXISTS countTable (onechar TEXT, count INT)")

for ch in inStr:
    if 'ㄱ' <= ch <= '힣':
        # SQL Injection 방지를 위해 파라미터를 사용하여 쿼리 실행
        cur.execute("SELECT * FROM countTable WHERE onechar = %s", (ch,))
        row = cur.fetchone()
        
        if row is None:
            # 새로운 문자인 경우 테이블에 추가
            cur.execute("INSERT INTO countTable VALUES (%s, %s)", (ch, 1))
        else:
            # 이미 존재하는 문자인 경우 빈도수 업데이트
            cnt = row[1]
            cur.execute("UPDATE countTable SET count = %s WHERE onechar = %s", (cnt + 1, ch))

# 변경사항 커밋
con.commit()

# 빈도수가 높은 순으로 정렬하여 출력
cur.execute("SELECT * FROM countTable ORDER BY count DESC")
print('원문\n', inStr)
print('----------------')
print('문자\t빈도수')
print('----------------')
while True:
    row = cur.fetchone()
    if row is None:
        break
    ch = row[0]
    cnt = row[1]
    print("%4s %5d" % (ch, cnt))

# count 개수를 막대그래프로 표시 

   
cur.execute("SELECT count,count(*) FROM countTable Group by count; ")
result = cur.fetchall()
x = []
y = []
for row in result :
    x.append(row[0])
    y.append(row[1])
con.close()
plt.bar(x,y)
plt.title('countTable')
plt.xlabel('char')
plt.ylabel('count')
plt.show()