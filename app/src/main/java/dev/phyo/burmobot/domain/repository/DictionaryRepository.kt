package dev.phyo.burmobot.domain.repository

import dev.phyo.burmobot.data.model.DictionaryEntry

interface DictionaryRepository {
    suspend fun getDictionary(): List<DictionaryEntry>
}