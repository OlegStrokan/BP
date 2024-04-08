#include <iostream>
#include <vector>
#include <numeric>

int main() {
  int array[] = {1, 2, 3, 4, 5};
  int sum_of_elements = accumulate(array.begin(), array.end(), 0);
  cout << "Sum of elements in the array: " << sum_of_elements << endl;
  return
