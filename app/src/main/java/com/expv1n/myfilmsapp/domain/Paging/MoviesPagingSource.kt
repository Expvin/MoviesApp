package com.expv1n.myfilmsapp.domain.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.expv1n.myfilmsapp.data.api.ApiFactory
import com.expv1n.myfilmsapp.domain.models.Film
import java.io.IOException

class MoviesPagingSource(
    private val service: ApiFactory
): PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.apiService.getPopularMovies(page = pageIndex)
            val movies = response.films
            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        }
        catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 7
    }
}