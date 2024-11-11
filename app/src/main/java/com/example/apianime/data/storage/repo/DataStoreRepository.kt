package com.example.apianime.data.storage.repo

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.apianime.domain.repo.IDataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * @author Evgenii
 */

val Context.dataStore by preferencesDataStore(name = "sorting")

object PreferenceKeys {
    val SORT = booleanPreferencesKey("isIncreased")
}

class DataStoreRepository(
    private val context: Context
) : IDataStoreRepository {
    override suspend fun save(isIncreased: Boolean) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[PreferenceKeys.SORT] = isIncreased
            }
        }
    }

    override fun get(): Flow<Boolean> {
        return context.dataStore.data.map{ prefs ->
            prefs[PreferenceKeys.SORT] ?: false
        }
    }
}