package com.benboonya.pokemoninfo.pokemon.repository

import com.benboonya.pokemoninfo.common.PokemonApi
import com.benboonya.pokemoninfo.pokemon.model.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getPokemonDetail(id: String): Pokemon? {
        return try {
            val response = api.getPokemonDetail(id)
            response
        } catch (ex: Exception) {
            null
        }
    }
}