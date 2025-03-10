package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.domain.repository.IDictionaryRepository
import javax.inject.Inject

class GetDictionaryUseCase @Inject constructor(private val repository: IDictionaryRepository) {
    suspend fun execute() = repository.getDictionary()
}