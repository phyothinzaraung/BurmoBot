package dev.phyo.burmobot.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phyo.burmobot.data.model.RecentEntry

@Dao
interface RecentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecentEntry(recentEntry: RecentEntry)

    @Query("SELECT * FROM recent_entries")
    suspend fun getRecentEntries(): List<RecentEntry>

    @Query("DELETE FROM recent_entries")
    suspend fun clearRecentEntries()
}