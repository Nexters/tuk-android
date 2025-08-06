package com.plottwist.core.preference.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthDataSource {

    override fun getAccessToken(): Flow<String?> =
        dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    override fun getRefreshToken(): Flow<String?> =
        dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }

    override fun getOnboardingCompleted(): Flow<Boolean?> =
        dataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETED]
        }


    override fun setAccessToken(accessToken: String): Flow<Unit> = flow {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    override fun setRefreshToken(refreshToken: String): Flow<Unit> = flow {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    override fun setOnboardingCompleted(completed: Boolean): Flow<Unit> = flow {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = completed
        }
    }

    override fun clear(): Flow<Unit> = flow {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = ""
            preferences[REFRESH_TOKEN] = ""
        }
    }

    companion object {
        const val TOKEN_PREFERENCES_NAME = "token_preferences"
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }
}
