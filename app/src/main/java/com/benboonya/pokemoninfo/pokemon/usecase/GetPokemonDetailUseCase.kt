package com.benboonya.pokemoninfo.pokemon.usecase

import com.benboonya.pokemoninfo.pokemon.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(url: String) = repository.getPokemonDetail(url)
}