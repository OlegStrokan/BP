use std::thread;
use std::time::Duration;

fn thread_function(id: usize) {
  println!("Thread {} started", id);
  thread::sleep(Duration::from_secs(2));
  println!("Thread {} finished", id);
}

fn main() {
  let mut handles = vec![];
  for i in 0..5 {
    let handle = thread::spawn(move || {
      thread_function(i);
    });
    handles.push(handle);
  }

  for handle in handles {
    handle.join().unwrap();
  }
  println!("All threads have finished");
}
