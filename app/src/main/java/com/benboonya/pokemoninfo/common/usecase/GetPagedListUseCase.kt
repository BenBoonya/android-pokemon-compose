package com.benboonya.pokemoninfo.common.usecase

import com.benboonya.pokemoninfo.common.repositpry.PagedListItemRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetPagedListUseCase @Inject constructor(
    private val repository: PagedListItemRepository
) {

    operator fun invoke(coroutineScope: CoroutineScope, dataType: String) =
        repository.getPagedList(dataType, coroutineScope)
}
