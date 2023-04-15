package com.expv1n.myfilmsapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.expv1n.myfilmsapp.data.api.ApiService
import com.expv1n.myfilmsapp.domain.models.Movies

class MoviesDataSource(val apiService: ApiService): PagingSource<Int, Movies>() {

    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        TODO("Not yet implemented")
    }
}