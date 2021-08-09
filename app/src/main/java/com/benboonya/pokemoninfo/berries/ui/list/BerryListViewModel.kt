package com.benboonya.pokemoninfo.berries.ui.list

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.benboonya.pokemoninfo.common.model.GenericListItem
import com.benboonya.pokemoninfo.common.usecase.GetPagedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BerryListViewModel @Inject constructor(
    getBerryListUseCase: GetPagedListUseCase
) : ViewModel() {

    val berryListResult: Flow<PagingData<GenericListItem>> =
        getBerryListUseCase(viewModelScope, "berry")
}