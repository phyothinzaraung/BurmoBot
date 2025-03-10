package dev.phyo.burmobot.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.phyo.burmobot.data.local.dao.DictionaryDao
import dev.phyo.burmobot.data.local.dao.FavouriteDao
import dev.phyo.burmobot.data.local.dao.RecentDao
import dev.phyo.burmobot.data.model.DictionaryEntry
import dev.phyo.burmobot.data.model.FavouriteEntry
import dev.phyo.burmobot.data.model.RecentEntry

@Database(entities = [DictionaryEntry::class, FavouriteEntry::class, RecentEntry::class], version = 1, exportSchema = true)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun favouriteDao(): FavouriteDao
    abstract fun recentDao(): RecentDao
}