import pymysql

con = pymysql.connect(host='127.0.0.1', user='root', password='chassisle1', db='naverdb', charset='utf8')
cur = con.cursor()

cur.execute("SELECT * FROM usertable")
result = cur.fetchall()
for row in result:
    print(row)

con.close()