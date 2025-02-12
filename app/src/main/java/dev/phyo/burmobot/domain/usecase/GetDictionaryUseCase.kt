package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend fun execute() = repository.getDictionary()
}