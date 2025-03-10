package dev.phyo.burmobot.domain.repository

import dev.phyo.burmobot.data.model.RecentEntry

interface IRecentRepository {
    suspend fun insertRecent(recentEntry: RecentEntry)

    suspend fun clearAllRecentEntries()

    suspend fun getRecentEntries(): List<RecentEntry>
}