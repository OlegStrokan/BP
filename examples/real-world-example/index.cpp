// Soft lock tour days
std::vector<TourDayId> softLockTourDays(const std::string& date = startOfToday()) {
  std::vector<TourDayId> lockedTourDayIds;

  for (const auto& unlockedTourDays : getTopChunkOfUnlockedTourDaysBefore(date)) {
    std::vector<TourDayId> tourDayIdsToLock;
    for (const auto& tourDay : unlockedTourDays) {
      tourDayIdsToLock.push_back(toId(tourDay));
    }

    repository.updateManyByIds(tourDayIdsToLock, {
      "lock_state": LockState::SoftLock,
    });

    metricService.tourDays.softLocked(tourDayIdsToLock.size());
    lockedTourDayIds.insert(lockedTourDayIds.end(), tourDayIdsToLock.begin(), tourDayIdsToLock.end());
  }

  return lockedTourDayIds;
}

// Get top chunk of unlocked tour days before
std::vector<TourDay> getTopChunkOfUnlockedTourDaysBefore(const std::string& before) {
  std::vector<TourDay> tourDays;
  while (true) {
    auto unlockedTourDays = repository.findManyUnlockedBeforeDate(before, {
      "offset": 0,
      "limit": TOUR_DAY_CHUNK_SIZE,
    });

    if (unlockedTourDays.empty()) {
      break;
    }

    tourDays.insert(tourDays.end(), unlockedTourDays.begin(), unlockedTourDays.end());
  }

  return tourDays;
}
