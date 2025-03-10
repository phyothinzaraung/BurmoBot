package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.domain.repository.IRecentRepository
import javax.inject.Inject

class GetRecentUseCase @Inject constructor(private val recentRepository: IRecentRepository) {
    suspend fun execute() = recentRepository.getRecentEntries()
}