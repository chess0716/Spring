aa = []

# 리스트 aa를 0으로 초기화
for i in range(0, 4):
    aa.append(0)

list_size = len(aa)

hap = 0

# 사용자로부터 4개의 숫자를 입력받아 리스트 aa에 저장
for i in range(0, list_size):
    aa[i] = int(input(str(i + 1) + "번째 숫자:"))

# 리스트 aa의 첫 4개 요소를 합하여 출력
hap = aa[0] + aa[1] + aa[2] + aa[3]

print("합계 ==> %d" % hap)