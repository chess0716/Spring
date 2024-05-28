import pymysql
from tkinter import *
from tkinter import messagebox

def insertData():
    # 데이터베이스 연결 및 커서 생성
    con, cur = None, None
    data1, data2, data3, data4 = '', '', '', ''
    sql = ''

    con = pymysql.connect(host='127.0.0.1', user='root', password='chassisle1', db='naverdb', charset='utf8')
    cur = con.cursor()

    # Entry 위젯에서 데이터 가져오기
    data1 = edt1.get()
    data2 = edt2.get()
    data3 = edt3.get()
    data4 = edt4.get()

    try:
        # SQL 쿼리 생성 및 실행
        sql = f"INSERT INTO usertable VALUES('{data1}', '{data2}', '{data3}', {data4})"
        cur.execute(sql)
    except:
        # 오류 발생 시 메시지 박스 표시
        messagebox.showerror('오류', '데이터 입력 오류가 발생함')
    else:
        # 성공 시 메시지 박스 표시 및 데이터베이스 커밋 후 연결 종료
        messagebox.showinfo('성공', '데이터 입력 성공')
        con.commit()
        con.close()

def selectData():
    # 데이터 조회를 위한 리스트 초기화
    strData1, strData2, strData3, strData4 = [], [], [], []
    
    # 데이터베이스 연결 및 커서 생성
    con = pymysql.connect(host='127.0.0.1', user='root', password='chassisle1', db='naverdb', charset='utf8')
    cur = con.cursor()

    # SELECT 쿼리 실행
    cur.execute('SELECT * FROM usertable')

    # 리스트에 헤더 추가
    strData1.append('사용자ID'); strData2.append('사용자이름')
    strData3.append('이메일'); strData4.append('출생년도')
    strData1.append('------------'); strData2.append('------------')
    strData3.append('------------'); strData4.append('------------')

    # 데이터베이스에서 데이터를 읽어와 리스트에 추가
    while (True):
        row = cur.fetchone()
        if row is None:
            break
        strData1.append(row[0]); strData2.append(row[1])
        strData3.append(row[2]); strData4.append(row[3])

    # 기존 리스트박스의 아이템 삭제
    listData1.delete(0, listData1.size()-1)
    listData2.delete(0, listData2.size()-1)
    listData3.delete(0, listData3.size()-1)
    listData4.delete(0, listData4.size()-1)

    # 리스트의 내용을 리스트박스에 추가
    for item1, item2, item3, item4 in zip(strData1, strData2, strData3, strData4):
        listData1.insert(END, item1)
        listData2.insert(END, item2)
        listData3.insert(END, item3)
        listData4.insert(END, item4)

    # 데이터베이스 연결 종료
    con.close()

# Tkinter GUI 설정
window = Tk()
window.geometry('600x300')
window.title('GUI 데이터 입력')

# 데이터 입력 프레임
edtFrame = Frame(window)
edtFrame.pack()

# 데이터 조회 프레임
listFrame = Frame(window)
listFrame.pack(side=BOTTOM, fill=BOTH, expand=1)

# 데이터 입력을 위한 Entry 위젯
edt1 = Entry(edtFrame, width=10)
edt1.pack(side=LEFT, padx=10, pady=10)
edt2 = Entry(edtFrame, width=10)
edt2.pack(side=LEFT, padx=10, pady=10)
edt3 = Entry(edtFrame, width=10)
edt3.pack(side=LEFT, padx=10, pady=10)
edt4 = Entry(edtFrame, width=10)
edt4.pack(side=LEFT, padx=10, pady=10)

# 데이터 입력 및 조회 버튼
btnInsert = Button(edtFrame, text="입력", command=insertData)
btnInsert.pack(side=LEFT, padx=10, pady=10)
btnSelect = Button(edtFrame, text="조회", command=selectData)
btnSelect.pack(side=LEFT, padx=10, pady=10)

# 조회 결과를 표시할 Listbox 위젯
listData1 = Listbox(listFrame, bg='yellow')
listData1.pack(side=LEFT, fill=BOTH, expand=1)
listData2 = Listbox(listFrame, bg='yellow')
listData2.pack(side=LEFT, fill=BOTH, expand=1)
listData3 = Listbox(listFrame, bg='yellow')
listData3.pack(side=LEFT, fill=BOTH, expand=1)
listData4 = Listbox(listFrame, bg='yellow')
listData4.pack(side=LEFT, fill=BOTH, expand=1)

# GUI 실행
window.mainloop()
