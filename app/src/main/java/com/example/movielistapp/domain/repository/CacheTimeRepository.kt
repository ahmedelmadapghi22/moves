package com.example.movielistapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CacheTimeRepository {
    suspend fun saveCacheTime(lastUpdateTime: Long)
    fun getCacheTime(): Flow<Long>

}