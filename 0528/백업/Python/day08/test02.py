import wordcloud
import matplotlib.pyplot as plt

keyword = {'안녕': 2, '하세요': 1, '빅데이터': 15, '웹크롤링': 3}

# 한글
wc = wordcloud.WordCloud(font_path='c:/windows/fonts/malgun.ttf')
cloud = wc.generate_from_frequencies(keyword)  # 수정된 부분

figure = plt.figure()
plt.imshow(cloud)
figure.savefig('word.png')

# figure를 화면에 출력
plt.show()
