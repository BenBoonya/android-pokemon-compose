package com.benboonya.pokemoninfo.common.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.benboonya.pokemoninfo.common.PokemonApi
import com.benboonya.pokemoninfo.common.model.GenericListItem
import java.lang.Exception

class PagedListDataSource(
    private val dataType: String,
    private val api: PokemonApi
) : PagingSource<String, GenericListItem>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, GenericListItem> {
        return try {
            val response = if (params.key != null) {
                api.getDataList(params.key!!)
            } else {
                api.getInitialDataList(dataType)
            }

            LoadResult.Page(
                data = response.results,
                prevKey = response.previous,
                nextKey = response.next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, GenericListItem>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }
}