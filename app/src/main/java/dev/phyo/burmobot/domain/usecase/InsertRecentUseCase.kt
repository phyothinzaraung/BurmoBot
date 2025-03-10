package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.data.model.RecentEntry
import dev.phyo.burmobot.domain.repository.IRecentRepository
import javax.inject.Inject

class InsertRecentUseCase @Inject constructor(private val recentRepository: IRecentRepository) {
    suspend fun execute(recentEntry: RecentEntry) = recentRepository.insertRecent(recentEntry)
}