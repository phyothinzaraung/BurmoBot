package dev.phyo.burmobot.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_entries")
data class RecentEntry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0L,
    var word: String? = null,
    val definition: String? = null
)
