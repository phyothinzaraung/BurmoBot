package dev.phyo.burmobot.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phyo.burmobot.data.model.DictionaryEntry

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary_entries")
    suspend fun getAllWords(): List<DictionaryEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<DictionaryEntry>)

    @Query("SELECT * FROM dictionary_entries where word = :word")
    suspend fun getDictionaryByWord(word: String): DictionaryEntry?
}