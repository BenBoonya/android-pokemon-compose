package com.benboonya.pokemoninfo.pokemon.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.benboonya.pokemoninfo.pokemon.model.Pokemon
import com.benboonya.pokemoninfo.pokemon.usecase.GetPokemonDetailUseCase
import com.benboonya.pokemoninfo.util.CoroutineRule

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PokemonDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    private lateinit var viewModel: PokemonDetailViewModel

    @Mock
    lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase

    @Before
    fun setUp() {
        viewModel = spy(
            PokemonDetailViewModel(
                getPokemonDetailUseCase
            )
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `assignArgument verify function calls`() = runBlockingTest {
        //GIVEN
        val args = PokemonDetailBottomSheetDialogFragmentArgs("url")
        whenever(getPokemonDetailUseCase.invoke(any())).thenReturn(
            Pokemon(3, 3, "", 4)
        )
        //WHEN
        viewModel.assignArgument(args)
        //THEN
        verify(viewModel).getPokemonDetail(any())
    }
}