package com.plottwist.core.preference.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
    }
}