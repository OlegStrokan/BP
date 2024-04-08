#include <iostream>
#include <curl/curl.h>

size_t writeCallback(void *ptr, size_t size, size_t nmemb, std::string *data) {
  data->append((char*)ptr, size * nmemb);
  return size * nmemb;

void sendRequests() {
  CURL *curl;
  CURLcode res;
  std::string data;

  for (int i = 1; i <= 5; ++i) {
    curl = curl_easy_init();
    if (curl) {
      curl_easy_setopt(curl, CURLOPT_URL, "https://api.example.com/data/" + std::to_string(i));
      curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeCallback);
      curl_easy_setopt(curl, CURLOPT_WRITEDATA, &data);
      res = curl_easy_perform(curl);
      std::cout << "Response for request " << i << ": " << res << std::endl;ij
      curl_easy_cleanup(curl);
    }
  }
}

int main() {
  sendRequests();
  return 0;
}
