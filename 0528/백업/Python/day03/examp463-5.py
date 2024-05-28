import pandas as pd
data = {
    ' 이름' : ['유정','유나','민영','은지'],
    ' 나이' : [30,28,31,29],
    '생일' : ['1991.5.2','1993.4.6','1990.9.12','1992.7.19']
}
index_data = ['하나','둘','셋','넷']
df = pd.DataFrame(data,index_data)
print(df)
#6번
import matplotlib.pyplot as plt
x_data = [10,20,30,40,50]
y_data = [1000,1500,3000,3500,5000]
plt.plot(x_data,y_data,color='green',linestyle=":",marker='o')
plt.show()

print(df.iloc[2,0])