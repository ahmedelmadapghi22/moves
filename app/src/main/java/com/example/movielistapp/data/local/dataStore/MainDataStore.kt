package com.example.movielistapp.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val CACHE_TIME_TITLE = "cacheTime"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "main_data_store")
val CACHE_TIME = longPreferencesKey(CACHE_TIME_TITLE)

class MainDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    fun getCacheTime() = context.dataStore.data.map { preferences ->
        preferences[CACHE_TIME] ?: 0
    }

    suspend fun saveCacheTime(lastUpdateTime: Long) {

        context.dataStore.edit { cacheTime ->
            cacheTime[CACHE_TIME] = lastUpdateTime
        }
    }

}