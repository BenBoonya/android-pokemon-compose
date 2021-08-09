package com.benboonya.pokemoninfo.common.repositpry

import androidx.paging.*
import com.benboonya.pokemoninfo.common.PokemonApi
import com.benboonya.pokemoninfo.common.datasource.PagedListDataSource
import com.benboonya.pokemoninfo.common.model.GenericListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagedListItemRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    fun getPagedList(
        dataType: String,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<GenericListItem>> {
        val pagingSource = PagedListDataSource(dataType, pokemonApi)

        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { pagingSource }
        ).flow.cachedIn(coroutineScope)
    }
}