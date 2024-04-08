import java.util.Arrays;

  public class Main {
  public static void main(String[] args) {
  int[] array = {1, 2, 3, 4, 5};
  int sumOfElements = Arrays.stream(array).sum();
  System.out.println("Sum of elements in the array: " + sumOfElements);
}
}

