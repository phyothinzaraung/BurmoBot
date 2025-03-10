package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.domain.repository.IDictionaryRepository
import javax.inject.Inject

class GetDictionaryByWordUseCase @Inject constructor(private val dictionaryRepository: IDictionaryRepository) {
    suspend fun execute(word: String) = dictionaryRepository.getDictionaryByWord(word)
}