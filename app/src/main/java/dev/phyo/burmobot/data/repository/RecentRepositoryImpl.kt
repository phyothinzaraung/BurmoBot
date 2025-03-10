package dev.phyo.burmobot.data.repository

import dev.phyo.burmobot.data.local.dao.RecentDao
import dev.phyo.burmobot.data.model.RecentEntry
import dev.phyo.burmobot.domain.repository.IRecentRepository

class RecentRepositoryImpl(
    private val recentDao: RecentDao
): IRecentRepository {
    override suspend fun insertRecent(recentEntry: RecentEntry) {
        recentDao.insertRecentEntry(recentEntry)
    }

    override suspend fun clearAllRecentEntries() {
        recentDao.clearRecentEntries()
    }

    override suspend fun getRecentEntries(): List<RecentEntry> {
        return recentDao.getRecentEntries()
    }
}