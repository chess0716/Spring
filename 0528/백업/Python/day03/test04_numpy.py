import numpy as np


d = np.array([2,3,4,5,6])
print(d)
print(d.shape)

e = np.array([[1,2,3,4],[3,4,5,6]])
print(e)
print(e.shape)
print(e.dtype)
print(np.zeros((2,10)))
print(np.ones((2,10)))
print(np.arange(2,10)) # 1차원배열 2이상 10미만

a = (np.ones((2,3)))
print(a)
b = np.transpose (a) # 행과 열이 바뀜
print(b)

arr1 = np.array([[2,3,4],[6,7,8]])
arr2 = np.array([[12,13,14],[26,27,28]])
print(arr1+arr2) #배열덧셈 == 같은자리의 원소끼리 덧셈
print(arr1-arr2)
print(arr1*arr2)
print(arr1/arr2)

##########################
# f = np.array([[1,2,3,4,5],[2,3,4,5,6],[5,6,7,8,9,9]])
# print(f)
f = np.array([[1,2,3,4,5],[2,3,4,5,6],[5,6,7,8,9,]])
f_list =[[1,2,3,4,5],[2,3,4,5,6],[5,6,7,8,9,9]]
print(f)
print(f_list)
d[:2]=0
print(np.arange(10))
arr4 = np.arange(10)+1 #각 원소에 +1
print(arr4)
print(arr4[:5])
print(arr4[-3:])
