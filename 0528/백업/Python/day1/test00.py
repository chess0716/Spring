# dict
d = dict()
print (d, type(d))
d = {
    'a' : 1,
    'b' : 2,
    'c' : 3
}
d1 = d.keys
print ("d1",d1)
d2 = d.items
print("d2" , d2)
d11 = d.keys()
print("keys" , d11)
d12 = d.values()
print ("values" , d12)

print("type list :", type(list(d.keys())))
print("values : ", d.values())
print("values set :", set(d.values()))

tt1 = 10,20,30
print(tt1)