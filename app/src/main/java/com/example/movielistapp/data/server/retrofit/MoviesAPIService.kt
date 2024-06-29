package com.example.movielistapp.data.server.retrofit

import com.example.movielistapp.data.server.apimodels.MovieImagesAPIResponse
import com.example.movielistapp.data.server.apimodels.MoviesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesAPIService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(MoviesAPIConstants.API_KEY_REQUEST_QUERY_PARAM) api_key: String = MoviesAPIConstants.API_KEY,
        @Query(MoviesAPIConstants.LANGUAGE_REQUEST_QUERY_PARAM) language: String = "en",
        @Query(MoviesAPIConstants.PAGE_REQUEST_QUERY_PARAM) page: Int = 1,
        @Query(MoviesAPIConstants.REGION_REQUEST_QUERY_PARAM) region: String = "ISO-3166-1",
    ): Response<MoviesResult>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query(MoviesAPIConstants.API_KEY_REQUEST_QUERY_PARAM) api_key: String = MoviesAPIConstants.API_KEY,
        @Query(MoviesAPIConstants.LANGUAGE_REQUEST_QUERY_PARAM) language: String = "en",
        @Query(MoviesAPIConstants.PAGE_REQUEST_QUERY_PARAM) page: Int = 1,
        @Query(MoviesAPIConstants.REGION_REQUEST_QUERY_PARAM) region: String = "ISO-3166-1",
    ): Response<MoviesResult>


    @GET("discover/movie")
    suspend fun searchMovie(
        @Query(MoviesAPIConstants.API_KEY_REQUEST_QUERY_PARAM) api_key: String = MoviesAPIConstants.API_KEY,
        @Query(MoviesAPIConstants.PRIMARY_RELEASE_YEAR_QUERY_PARAM) primaryReleaseYear: Int,
        @Query(MoviesAPIConstants.ADULT_QUERY_PARAM) isAdult: Boolean = false,
        @Query(MoviesAPIConstants.PAGE_REQUEST_QUERY_PARAM) page: Int = 1

    ): Response<MoviesResult>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path(MoviesAPIConstants.MOVIE_ID_QUERY_PARAM) movieID: Int,

        @Query(MoviesAPIConstants.API_KEY_REQUEST_QUERY_PARAM) api_key: String = MoviesAPIConstants.API_KEY,
    ): Response<MovieImagesAPIResponse>
}