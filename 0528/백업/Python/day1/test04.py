

def max_counts(nums):
    counts = {}
    for num in nums:
        if num in counts:
            counts[num] += 1
        else :
            counts[num] = 1
    return counts


nums = [1,1,1,2,2,3,2,3,2,3,3,3,1]
counts = max_counts(nums)
result = max_counts(nums)
for num, count in counts.items():
    print(num,":",count,"번")
#
# 
#  dict에서 최대 value 출력


print(max(counts.values()))