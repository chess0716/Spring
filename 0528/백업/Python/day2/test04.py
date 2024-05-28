import requests

URL = 'https://www.naver.com'
response = requests.get(URL)
print(response.text)