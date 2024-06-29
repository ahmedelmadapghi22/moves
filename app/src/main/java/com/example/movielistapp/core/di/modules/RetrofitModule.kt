package com.example.movielistapp.core.di.modules

import com.example.movielistapp.data.server.retrofit.MoviesAPIService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheSize = (10 * 1024 * 1024).toLong()

        val cache = Cache(File("okhttp.cache"), cacheSize)


        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).cache(cache).build()

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(client)
            .baseUrl(BASE_URL).build()


    }

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MoviesAPIService {
        return retrofit.create(MoviesAPIService::class.java)
    }

}