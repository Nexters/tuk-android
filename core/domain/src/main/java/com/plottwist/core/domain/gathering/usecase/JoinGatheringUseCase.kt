package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.JoinGatheringRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JoinGatheringUseCase @Inject constructor(
    private val repository: JoinGatheringRepository
) {
    operator fun invoke(gatheringId: Long): Flow<Result<Long>> = flow {
        emit(repository.joinGathering(gatheringId))
    }
}