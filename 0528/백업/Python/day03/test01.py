import re
example = '저는 (91년)에 태어났습니다. (97년)에는 imf가 있었습니다 지금은 (2023년)입니다.'

print(example)

txt = re.findall(r'\(\d+년\)', example)
print(txt)

