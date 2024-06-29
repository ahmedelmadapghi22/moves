package com.example.movielistapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movielistapp.data.server.apimodels.MovieAPIModel
import com.example.movielistapp.data.server.datasource.MovieNetworkDataSource

class SearchMoviesPagingSource constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val releaseYear: Int,
    private val isAdult: Boolean
) :
    PagingSource<Int, MovieAPIModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieAPIModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieAPIModel> {
        val page = params.key ?: 1
        return try {
            val response = movieNetworkDataSource.searchMovies(
                releaseYear = releaseYear,
                isAdult = isAdult,
                page = page
            )
            val movies = response.body()?.movieAPIModels ?: emptyList()
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}
