package dev.phyo.burmobot.domain.repository

import dev.phyo.burmobot.data.model.DictionaryEntry

interface IDictionaryRepository {
    suspend fun getDictionary(): List<DictionaryEntry>
}