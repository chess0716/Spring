# 정규표현식 : 특정한 규칙을 가진 문자열을 표현하기 위해 사용하는 형식
# 1) . : 1개 문자와 일치
# 2) [] : 대괄호 안의 문자중 하나 선택
#3) * : 0개이상의 문자를 포함

import re
a = "1,2,3,4,5"
b = '''
이길동, 01-1111-1111, 11, 0000
홍길동, 02-1111-2222, 21, 1234
박길동, 051-0000-0000, 34, 4567
'''
pattern = re.compile('[^0-9]') # ^0-9를 제외한 것
result = re.sub(pattern,'',a)
print(result)

pattern2 = re.compile('[^가-힣]')
result2 = re.sub(pattern2,'',b)
print(result2)

