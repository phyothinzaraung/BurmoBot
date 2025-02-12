package dev.phyo.burmobot.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_entries")
data class DictionaryEntry(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Long,
    @ColumnInfo(index = true)
    val word: String? = null,
    @ColumnInfo(name = "stripword", index = true)
    val stripWord: String? = null,
    val title: String? = null,
    val definition: String? = null,
    val keywords: String? = null,
    val synonym: String? = null,
    val picture: String? = null
)
