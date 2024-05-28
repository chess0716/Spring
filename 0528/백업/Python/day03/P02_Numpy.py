import  numpy as np

'''
Numpy: 대규모 다차원 배열을 쉽게 처리 할 수 있도록 도와주는 파이썬의 외부 모듈
기본적으로 array라는 자료형으로 사용, 행렬 개념과 비슷
'''
Lists = [["홍길동", 85, 95, 100],
         ["박길동", 100, 95, 85]]

# "Lists" 출력
print(Lists, type(Lists))
print(Lists[1], Lists[0][0])

# "Lists"의 값 출력
for i in range(len(Lists)):
    for j in range(len(Lists[i])):
        print(Lists[i][j], end=' ')


park_scores = Lists[1][1:]
sum_park = sum(park_scores)
print("박길동의 성적 합:", sum_park)

listStr = ["Life","is","Good","and","Happy"]
print(listStr)
listStr.reverse(); print(listStr)

#리스트 : 요소를 기준으로 인덱싱 , 넘파이행렬 : 위치를 기준으로 인덱싱
a = [[1,2,3],[4,5,6],[7,8,9]]
b = np.array(a)
print(b[ :,2])
print(b[1,:2])
print("b", b[:3])

# array[값] 값에 해당하는 인덱스를 추출
# array[:] array의 모든 성분을 추출

arr = np.arange(10)
print(arr)
print(arr[9])
print(arr[:])
print(arr[1:3])
arr[5:8] = 12
print(arr)

# array[행,열] : 단일차원과 달리 다차원에서는 콤마를 기준으로 행과 열로 분리
# array[행 시작,행 끝: 열시작 , 열 끝]
print(a)

import common.constant as const
import numpy as np

const.PI = 3.14
print(const.PI)



const.SIZE = 5
npArr = np.random.randint(8, 255, size=(const.SIZE, const.SIZE))
print(npArr)


npArr2 = []
for _ in range(const.SIZE):
    tmpArr = []
    for _ in range(const.SIZE):
        tmpArr.append(_)
    npArr2.append(tmpArr)
print(npArr2)

npArr += 1
print(npArr)

tot = 0; cnt = 0
for i in range(len(npArr)):
    for j in  range(len(npArr[i])):
        tot += npArr[i][j]; cnt += 1
    print(f'Total:{tot}/ Average: {tot/cnt}')