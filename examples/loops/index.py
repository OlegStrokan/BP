import requests

def send_requests():
for i in range(1, 6):
response = requests.get(f"https://api.example.com/data/{i}")
print(f"Response for request {i}: {response.status_code}")

send_requests()
