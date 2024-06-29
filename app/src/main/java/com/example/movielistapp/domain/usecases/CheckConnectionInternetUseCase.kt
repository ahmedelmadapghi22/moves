package com.example.movielistapp.domain.usecases

import android.content.Context
import com.example.movielistapp.data.util.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckConnectionInternetUseCase @Inject constructor(@ApplicationContext private val context: Context) {

    operator fun invoke(): Boolean {
        return NetworkUtils.isInternetAvailable(context)
    }


}