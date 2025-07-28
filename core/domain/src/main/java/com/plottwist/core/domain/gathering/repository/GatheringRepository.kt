package com.plottwist.core.domain.gathering.repository

import com.plottwist.core.domain.model.Gatherings

interface GatheringRepository {
    suspend fun getGatherings(): Result<Gatherings>
}
