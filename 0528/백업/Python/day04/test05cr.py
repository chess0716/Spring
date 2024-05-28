from bs4 import BeautifulSoup
import urllib.request

urlLotto = 'https://dhlottery.co.kr/gameResult.do?method=byWin'
htmlObject = urllib.request.urlopen(urlLotto)
webPage = htmlObject.read()
bsObject = BeautifulSoup(webPage, 'html.parser')

# 수정된 셀렉터
numbers = bsObject.select('#article > div:nth-child(2) > div > div.win_result > div > div.num.win > p > span')
bonus = bsObject.select_one('#article > div:nth-child(2) > div > div.win_result > div > div.num.bonus > p > span')

numList = []
for number in numbers:
    numList.append(number.text)
numList.append(bonus.text)

print(numList)
