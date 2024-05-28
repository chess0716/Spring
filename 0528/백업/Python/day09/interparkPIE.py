import pymysql as py
import matplotlib.pyplot as plt

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'pythondb'
charset = 'utf8mb4'


con = py.connect(host=host, user=user, password=password, db=db, charset=charset)
cur = con.cursor()

cur.execute('SELECT * FROM hotel1 WHERE hotelPrice > 50000')
result50k = cur.fetchall()

cur.execute('SELECT * FROM hotel1 WHERE hotelPrice > 100000 and hotelPrice <= 200000')
result100k = cur.fetchall()

cur.execute('SELECT * FROM hotel1 WHERE hotelPrice > 200000')
result200k = cur.fetchall()


sizes = [
    len(result50k),
    len(result100k),
    len(result200k)
]




labels = ['price more than 50000', 'price more than 100000', ' price more than 200000 ']
plt.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=90)
plt.axis('equal')
plt.title('Hotel Price')


plt.show()

con.close()

