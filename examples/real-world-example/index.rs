async fn soft_lock_tour_days(date: String) -> Vec<u32> {
  println!("Soft locking tour days");

  let mut locked_tour_day_ids = Vec::new();

  let mut tour_days_stream = get_top_chunk_of_unlocked_tour_days_before(date).await;

  while let Some(unlocked_tour_days) = tour_days_stream.next().await {
  let tour_day_ids_to_lock: Vec<u32> = unlocked_tour_days.iter().map(|tour_day| tour_day.id).collect();

  update_many_by_ids(&tour_day_ids_to_lock, LockState::SoftLock).await;

  metric_service.tour_days.soft_locked(tour_day_ids_to_lock.len());

  locked_tour_day_ids.extend(tour_day_ids_to_lock);
}

locked_tour_day_ids
}

async fn get_top_chunk_of_unlocked_tour_days_before(before: String) -> impl Stream<Item = Vec<TourDay>> {
  let mut offset = 0;

  futures::stream::try_unfold(offset, move |offset| async move {
    let result = repository.find_many_unlocked_before_date(before.clone(), offset, TOUR_DAY_CHUNK_SIZE).await?;

    if result.items.is_empty() {
  return Ok(None);
}

let tour_days = result.items;

Some((tour_days, offset + TOUR_DAY_CHUNK_SIZE))
})
}
