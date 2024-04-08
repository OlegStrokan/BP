class MyThread extends Thread {
  private int id;

  public MyThread(int id) {
  this.id = id;
}

public void run() {
  System.out.println("Thread " + id + " started");
  try {
    Thread.sleep(2000);
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
  System.out.println("Thread " + id + " finished");
}
}

public class Main {
  public static void main(String[] args) {
  Thread[] threads = new Thread[5];
  for (int i = 0; i < 5; ++i) {
  threads[i] = new MyThread(i);
  threads[i].start();
}

try {
  for (Thread thread : threads) {
    thread.join();
  }
} catch (InterruptedException e) {
  e.printStackTrace();
}
System.out.println("All threads have finished");
}}

