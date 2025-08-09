package com.plottwist.core.domain.push.repository

interface PushRepository {
    suspend fun updateFcmToken(fcmToken: String) : Result<Unit>
}
