async def soft_lock_tour_days(date=start_of_today().isoformat()) -> List[TourDay['id']]:
locked_tour_day_ids: List[TourDay['id']] = []

async for unlocked_tour_days in self.get_top_chunk_of_unlocked_tour_days_before(date):
tour_day_ids_to_lock = [to_id(tour_day) for tour_day in unlocked_tour_days]

await self.repository.update_many_by_ids(tour_day_ids_to_lock, {
  'lock_state': LockState.SoftLock,
})

self.metric_service.tour_days.soft_locked(len(tour_day_ids_to_lock))
locked_tour_day_ids.extend(tour_day_ids_to_lock)

return locked_tour_day_ids

async def get_top_chunk_of_unlocked_tour_days_before(before: ISO8601NoTime) -> AsyncGenerator:
  while True:
tour_days = await self.repository.find_many_unlocked_before_date(before, {
  'offset': 0,
  'limit': TOUR_DAY_CHUNK_SIZE,
})

if not tour_days:
  break

yield tour_days
