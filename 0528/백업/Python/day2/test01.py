

def max_counts(nums):
    counts = {}
    for num in nums:
        if num in counts:
            counts[num] += 1
        else :
            counts[num] = 1
    return counts



print("양수를 입력하세요 종료는 -1")
nums = []
while True:
    
    i = int (input())
    if i == -1:
        break
    nums.append(i)


#양수를 입력받아 마지막은 -1 (종료)dict 형태로 출력
counts = max_counts(nums)
print(counts)