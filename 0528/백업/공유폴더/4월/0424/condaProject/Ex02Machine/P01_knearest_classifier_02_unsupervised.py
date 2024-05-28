import pandas as pd
import matplotlib.pyplot as plt
from sklearn.neighbors import KNeighborsClassifier
import numpy as np

figure = plt.figure()
data = pd.read_csv('Fish.csv')

bream_length = data.loc[data.Species == 'Bream', ['Length2']].iloc[:,0].to_list()

bream_weight = data.loc[data.Species == 'Bream', ['Weight']].iloc[:,0].to_list()

smelt_length = data.loc[data.Species == 'Smelt', ['Length2']].iloc[:,0].to_list()

smelt_weight = data.loc[data.Species == 'Smelt', ['Weight']].iloc[:,0].to_list()

# 머신러닝 알고리즘은 크게 지도학습과 비지도학습으로 나뉜다.
# 비지도학습(Unsupervised Learning) 타깃 없이 입력 데이터만 사용