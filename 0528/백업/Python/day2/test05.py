import codecs
import re

f = codecs.open('friends101.txt','r',encoding='utf-8')
script101 = f.read()

print(script101[:100])
# Monica:
Line =re.findall(r'Monica:.',script101)
print(Line)
print(Line[:3])
print(type(Line))

# All : 대사 출력
All = re.findall(r'All:.+',script101)
print(All)
#All의 마지막 대사 출력
print(All[-1])
print(len(All))
#출연진 만 출력
char = re.compile(r'\b([A-Z][a-z]+):')
print(set(re.findall(char , script101)))
y = set(re.findall(char , script101))
print(type(y))
lst = list(y)
print(type(lst))
character = []
for i in lst :
    character += [i]
print('출연진 : ',character)


character2 = []
for i in lst:
    ch =re.sub(':','',i)
    character2.append(ch)
print(character2)
print()
print()
# 지문 만 출력
s = re.compile(r'\([A-Za-z].+[a-z|.]\)')
print(set(re.findall(s,script101)))
print(s)

s2 = re.compile(r'\([A-Za-z].+[a-z|.]\)')
print(set(re.findall(s2,script101)))
print(s2)

#############################
a = '제 이메일 주소는 great@naver.com'
a += '오늘은 today@naver.com 내일은 apple@gmail.com   life@abc.co.kr 라는 메일을 사용합니다 '
a1 = re.compile(r'[a-z]+@[a-z]+.[a-z]+.[a-z]')
print(set(re.findall(a1,a)))
print(a1)


words = ['apple','cat','brave','drama','asise','blow','coat','advove']
# a로 시작하는 단어출력
alist = []
for i in words:
    alist += re.findall(r'a[a-z]+',i)
print ('alist :',alist)


for i in words:
    blist = re.search(r'a[a-z]+',i)
    if blist:
        print (blist.group()) # blist 생성되지 않은 상태에서 group() 사용해서 오류

print()
for i in words:
    m = re.match(r'a[a-z]+',i) # pattern 을 문자열의 첫부분과 비교
    if m :
        print(m.group())



