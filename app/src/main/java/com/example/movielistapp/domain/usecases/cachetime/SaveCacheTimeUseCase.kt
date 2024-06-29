package com.example.movielistapp.domain.usecases.cachetime

import com.example.movielistapp.domain.repository.CacheTimeRepository
import javax.inject.Inject

class SaveCacheTimeUseCase @Inject constructor(private val cacheTimeRepository: CacheTimeRepository) {
    suspend operator fun invoke() {
        cacheTimeRepository.saveCacheTime(System.currentTimeMillis())
    }


}