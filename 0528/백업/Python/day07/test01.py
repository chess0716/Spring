import urllib.request
from bs4 import BeautifulSoup
import pymysql

host = '127.0.0.1'
user = 'root'
password = 'chassisle1'
db = 'naverdb'
charset = 'utf8mb4'

try:
    con = pymysql.connect(host=host, user=user, password=password, db=db, charset=charset)
    cur = con.cursor()

    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'}

    melonurl = 'https://www.melon.com/chart/index.htm'
    request = urllib.request.Request(melonurl, headers=headers)

    htmlObject = urllib.request.urlopen(request)
    webPage = htmlObject.read()
    bsObject = BeautifulSoup(webPage, 'html.parser')

    melontag = bsObject.select('#frm > div > table > tbody tr')

    melondata = []

    for tag in melontag:
        rank = tag.select_one('span.rank').text
        title = tag.select_one('div.ellipsis.rank01 >span > a').text
        artist = tag.select_one('div.ellipsis.rank02 > a').text
        album = tag.select_one('div.ellipsis.rank03 > a').text

        melondata.append([rank, title, artist, album])

    for data in melondata:
        rank, title, artist, album = data


        
    sql = "INSERT INTO melon (`rank`, `title`, `artist`, `album`) VALUES (%s, %s, %s, %s)"
    cur.execute(sql, (rank, title, artist, album))

    
    cur.execute("SELECT * FROM melon")
    rows = cur.fetchall()
    
   
    for row in rows:
        print(row)

    con.commit()

except Exception as e:
    print(f"Error: {e}")




