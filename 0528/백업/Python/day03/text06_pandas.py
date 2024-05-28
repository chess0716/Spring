import pandas as pd
data = { 
    'name' : ['aaa','bbb','ccc','ddd'],
    'age' : [33,44,55,66],
    'score' : [90.2,88.5,45.8,78.3]
}
# dic ==> dataFrame 으로  

df = pd.DataFrame(data)
print(df)
print(df.sum())
# print(df.mean())  평균
