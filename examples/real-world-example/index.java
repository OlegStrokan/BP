public List<Integer> softLockTourDays(String date) {
  System.out.println("Soft locking tour days");

  List<Integer> lockedTourDayIds = new ArrayList<>();

  Stream<List<TourDay>> tourDaysStream = getTopChunkOfUnlockedTourDaysBefore(date);

  tourDaysStream.forEach(unlockedTourDays -> {
    List<Integer> tourDayIdsToLock = new ArrayList<>();
    unlockedTourDays.forEach(tourDay -> tourDayIdsToLock.add(tourDay.getId()));

    updateManyByIds(tourDayIdsToLock, LockState.SoftLock);

    metricService.tourDays.softLocked(tourDayIdsToLock.size());

    lockedTourDayIds.addAll(tourDayIdsToLock);
  });

  return lockedTourDayIds;
}

private Stream<List<TourDay>> getTopChunkOfUnlockedTourDaysBefore(String before) {
  int offset = 0;

  return Stream.generate(() -> {
    List<TourDay> tourDays = repository.findManyUnlockedBeforeDate(before, offset, TOUR_DAY_CHUNK_SIZE).getItems();

    if (tourDays.isEmpty()) {
      return null;
    }

    offset += TOUR_DAY_CHUNK_SIZE;

    return tourDays;
  }).takeWhile(tourDays -> tourDays != null);
}
