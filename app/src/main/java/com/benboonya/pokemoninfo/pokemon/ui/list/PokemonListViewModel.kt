package com.benboonya.pokemoninfo.pokemon.ui.list

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.benboonya.pokemoninfo.common.model.GenericListItem
import com.benboonya.pokemoninfo.common.usecase.GetPagedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    getPokemonListUseCase: GetPagedListUseCase
) : ViewModel() {

    val pokemonListResult: Flow<PagingData<GenericListItem>> =
        getPokemonListUseCase(viewModelScope, "pokemon")
}