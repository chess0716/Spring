import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import matplotlib as mpl

# 한글 폰트 설정
font_path = 'c:/Windows/fonts/malgun.ttf'
font_prop = mpl.font_manager.FontProperties(fname=font_path, size=14)
font_name = font_prop.get_name()

plt.rcParams['font.family'] = font_name
plt.rcParams['font.size'] = 12

years = [2015, 2017, 2016, 2018, 2019, 2020]
quarters = ['1분기', '2분기', '3분기', '4분기']
values = [500, 450, 510, 610,
          690, 760, 800, 900, 
          1100, 1030, 1200, 1380,
          1500, 1650, 1700, 1800, 
          1980, 2520, 2300, 2450,
          1020, 1600, 2200, 2550]
reshaped_values = np.reshape(values, (-1, len(quarters)))

df = pd.DataFrame(reshaped_values, index=years, columns=quarters)

df.plot(kind='bar', figsize=(10, 6))
plt.title('2015 ~ 2020 Quarters Sales')
plt.xlabel('Years')
plt.ylabel('Sales')
plt.legend(title='Sales', loc='upper right')

# x 축의 눈금 설정
x_positions = np.arange(len(years))
x_labels = years
plt.xticks(x_positions, x_labels, fontsize=10)

plt.show()
