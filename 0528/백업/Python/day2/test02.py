
def multi(v1, v2):
    res1 = v1 + v2
    res2 = v1 - v2
    return res1,res2


hap,sub = multi(100, 200)


print("multi()에서 돌려준 값 ==> %d, %d" % (hap,sub))