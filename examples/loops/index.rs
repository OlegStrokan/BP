use reqwest;

async fn send_requests() -> Result<(), reqwest::Error> {
  for i in 1..=5 {
  let response = reqwest::get(format!("https://api.example.com/data/{}", i)).await?;
  println!("Response for request {}: {}", i, response.status());
}
Ok(())
}

#[tokio::main]
async fn main() -> Result<(), reqwest::Error> {
  send_requests().await?;
  Ok(())
}
