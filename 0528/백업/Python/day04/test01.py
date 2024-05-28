import re
# 1 ~ 10 까지의 합 출력
total_sum = sum(range(1, 11))
print (total_sum)


# 2. 구구단

for i in range(2, 10):  
    print(f"{i}단:")
    for j in range(1, 10): 
        result = i * j
        print(f"{i} x {j} = {result}")

# 3 년도만 출력
exam = " 저는 92년도에 ~~~~ 88년에 ~~"
txt1 = re.findall(r'\d.+?년', exam)
print(txt1)



# 4. "."으로 구분하여 문장 출력
d = ' i have a dog. iam not a girl. you are not alone'
s = d.split('. ')
print(s)

# 5.
txt = "Phoebe: Just, 'cause, I don't want her to go through what I went through with Carl- oh!"
txt += "Monica: Okay, everybody relax."
txt += "This is not even a date."
txt += "It's just two people going out to dinner and- not having sex."
txt += "Chandler: Sounds like a date to me."
txt += "Chandler: Alright, so I'm back in high school, I'm standing in the middle of the cafeteria, and I realize I am totally naked."

# 등장인물 출력
ch = re.findall(r'[A-Z][a-z]+:',txt)
print(ch)

# 등장인물 제거하고 출력
print(set(ch))

# 6 문자열에서 무작위로 5개 문자 추출하여 새로운 변수 pw에 하나씩 병합
import random

chars = '한글우수'
pw = str()

for _ in range(5):
    random_char = random.choice(chars)
    pw += random_char

print("생성된 비밀번호:", pw)

# 7. animal 리스트에서 새가 저장되어 있는 위치 (인덱스만) 저장하는 리스트
animals = ['새','코끼리','강아지','새','강아지','새']  
bird_indices = [index for index, animal in enumerate(animals) if animal == '새']
print("새가 저장되어 있는 위치(인덱스) 리스트:", bird_indices)  
for index, animal in enumerate(animals):
    print(f"인덱스: {index}, 동물: {animal}")

bird_pos = []
for b in range(len(animals)):
    if(animals[b] == '새'):
        bird_pos.append(b)
        
print(bird_pos)
# 8 mylist 에서 짝수만 출력
new_list = []
mylist = [3,5,4,9,2,8,2,1]
for i in mylist:
    if i % 2 == 0:
        new_list.append(i)
print(new_list)
# 리스트 함축 : for 문과 if 조건식을 함축적으로 결합한 형식
new_list2 = [i for i in mylist if(i%2)==0]
print(new_list2)

import pandas as pd
# survey.csv 위에서 5개 출력
df = pd.read_csv ('survey.csv', encoding='cp949')
print(df.stress.mean())
# 수입 합계
print(df.income.sum())
# 중앙값
print(df.income.median())
# describe()
print(df.describe())
print(df.sex.value_counts())