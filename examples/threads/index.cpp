include <iostream>
#include <thread>
#include <vector>
void threadFunction(int id) {
  std::cout << "Thread " << id << " started" << std::endl;
  std::this_thread::sleep_for(std::chrono::seconds(2));
  std::cout << "Thread " << id << " finished" << std::endl;
}

int main() {
  std::vector<std::thread> threads;
  for (int i = 0; i < 5; ++i) {
    threads.emplace_back(threadFunction, i);
  }

  for (auto& thread : threads) {
    thread.join();
  }

  std::cout << "All threads have finished" << std::endl;  return 0; }
