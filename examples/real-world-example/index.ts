softLockTourDays = async (
    date = toISO8601NoTimeUTC(startOfToday()),
): Promise<Array<TourDay['id']>> => {
    this.logger.info('Soft locking tour days')

    const lockedTourDayIds: Array<TourDay['id']> = []

    for await (const unlockedTourDays of this.getTopChunkOfUnlockedTourDaysBefore(date)) {
        const tourDayIdsToLock = unlockedTourDays.map(toId)

        await this.repository.updateManyByIds(tourDayIdsToLock, {
            lockState: LockState.SoftLock,
        })

        this.metricService.tourDays.softLocked(tourDayIdsToLock.length)
        lockedTourDayIds.push(...tourDayIdsToLock)
    }

    return lockedTourDayIds
}

private async *getTopChunkOfUnlockedTourDaysBefore(before: ISO8601NoTime) {
    while (true) {
        const { items: tourDays } = await this.repository.findManyUnlockedBeforeDate(before, {
            // The offset is always 0 since we are mutating tour days from the top
            offset: 0,
            limit: TOUR_DAY_CHUNK_SIZE,
        })

        if (tourDays.length === 0) break

        yield tourDays
    }
}
