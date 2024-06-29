package com.example.movielistapp.domain.usecases.cachetime

import com.example.movielistapp.domain.repository.CacheTimeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InValidateDataUseCase @Inject constructor(private val cacheTimeRepository: CacheTimeRepository) {
    suspend operator fun invoke() = flow {
        cacheTimeRepository.getCacheTime().collect { cacheTime ->
            if (System.currentTimeMillis() - cacheTime >= 4 * 60 * 60 * 1000) {
                emit(cacheTime)
            } else
                // Zero mean the are no cache happen
                emit(0)

        }
    }

}