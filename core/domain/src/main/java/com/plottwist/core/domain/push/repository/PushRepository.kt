package com.plottwist.core.domain.push.repository

import kotlinx.coroutines.flow.Flow

interface PushRepository {
    suspend fun updateFcmToken() : Result<Unit>
    fun getFcmToken() : Flow<Result<String>>
}
