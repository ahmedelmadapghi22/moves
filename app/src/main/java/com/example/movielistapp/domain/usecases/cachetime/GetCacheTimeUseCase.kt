package com.example.movielistapp.domain.usecases.cachetime

import com.example.movielistapp.domain.repository.CacheTimeRepository
import javax.inject.Inject

class GetCacheTimeUseCase @Inject constructor(private val cacheTimeRepository: CacheTimeRepository) {
     operator fun invoke() {
        cacheTimeRepository.getCacheTime()
    }


}