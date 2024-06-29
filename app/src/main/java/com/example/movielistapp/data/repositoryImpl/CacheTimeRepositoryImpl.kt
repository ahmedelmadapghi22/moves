package com.example.movielistapp.data.repositoryImpl

import com.example.movielistapp.data.local.dataStore.MainDataStore
import com.example.movielistapp.domain.repository.CacheTimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheTimeRepositoryImpl @Inject constructor(private val mainDataStore: MainDataStore) :
    CacheTimeRepository {
    override suspend fun saveCacheTime(lastUpdateTime: Long) {
        mainDataStore.saveCacheTime(lastUpdateTime)
    }

    override fun getCacheTime(): Flow<Long> {
        return mainDataStore.getCacheTime()
    }


}