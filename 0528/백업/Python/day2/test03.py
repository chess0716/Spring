import re


text = "<title>지금은 문자열 연습입니다.</title>"
print(text[0:7])
print(text.find('문')) # 있으면 위치값 리턴
print(text.find('파')) # 없으면 -1 리턴
print(text.index('문')) # 있으면 위치값 리턴
# print(text.index('파')) # 없으면 오류

text1 = "      <title>지금은 문자열 연습입니다.</title>        "
print(len(text1))
print(text1.strip())
print(len(text1.strip()))
print(text.lstrip())
print(len(text.lstrip()))
print(text.rstrip())
print(len(text.rstrip()))
text2 = ";"
print(text1+text2)

print(text.replace('<title>','<div>'))
up = text.upper()
print(up)
print(up.lower())

text3 = "<head>안녕하세요</head?"
body_match = re.search(r'<head>.*/</head>', text3)
if body_match:
    body = body_match.group()
    print(body)
else:
    print("일치하는 문자열이 없습니다.")
    # [0-9] , [a,z]
    # ab*c : abc , abbc , abbbc, abbbbc
    # *(0번이상) +(1이상) ?(0이상 1이하)
print ("===================================")
text4 = '<head>안녕하세요...<title>지금은 문자열연습</title></head>'
body = re.search(r'<title>.*?</title>', text4)
body = body.group()
print(body)

body1 = re.search('<.+?>', body)
print('body1:', body1.group())

body2 = re.search('<.+>', body)
print('body2:', body2.group())














