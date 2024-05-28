import pymysql

# MySQL 연결 설정
con = pymysql.connect(host='127.0.0.1', user='root', password='chassisle1', db='naverdb', charset='utf8')
cur = con.cursor()

# 사용자 정보 입력 받기
while True:
    data1 = input('사용자 아이디 ==> ')
    if data1 == '':
        break
    data2 = input('사용자 이름 ==> ')
    data3 = input('이메일 ==> ')
    data4 = input('출생년도 ==> ')

    

    sql = f"INSERT INTO usertable VALUES('{data1}', '{data2}', '{data3}', {data4})"
    cur.execute(sql)



con.commit()
con.close()

