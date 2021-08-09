package com.benboonya.pokemoninfo.berries.ui.detail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benboonya.pokemoninfo.berries.model.Berry
import com.benboonya.pokemoninfo.berries.usecase.GetBerryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerryDetailViewModel @Inject constructor(
    private val getBerryDetailUseCase: GetBerryDetailUseCase
) : ViewModel() {
    val berryDetail: MutableLiveData<Berry> = MutableLiveData()

    val isLoading: MediatorLiveData<Boolean> = MediatorLiveData()

    fun getBerryDetail(url: String) = viewModelScope.launch {
        isLoading.value = true
        berryDetail.value = getBerryDetailUseCase(url)
        isLoading.value = false
    }
}