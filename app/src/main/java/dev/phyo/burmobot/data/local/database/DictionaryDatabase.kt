package dev.phyo.burmobot.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.phyo.burmobot.data.local.dao.DictionaryDao
import dev.phyo.burmobot.data.model.DictionaryEntry

@Database(entities = [DictionaryEntry::class], version = 1, exportSchema = true)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}