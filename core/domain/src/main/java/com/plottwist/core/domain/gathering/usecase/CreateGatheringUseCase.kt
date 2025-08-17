package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.model.CreateGatheringModel
import com.plottwist.core.domain.gathering.repository.CreateGatheringRepository
import javax.inject.Inject

class CreateGatheringUseCase @Inject constructor(
    private val repository: CreateGatheringRepository
){
    suspend operator fun invoke(model: CreateGatheringModel):Result<Int> {
        return repository.createGathering(model)
    }
}