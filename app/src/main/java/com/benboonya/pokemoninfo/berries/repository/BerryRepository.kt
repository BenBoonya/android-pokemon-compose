package com.benboonya.pokemoninfo.berries.repository

import com.benboonya.pokemoninfo.berries.model.Berry
import com.benboonya.pokemoninfo.common.PokemonApi
import javax.inject.Inject

class BerryRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getBerryDetail(url: String): Berry? {
        return try {
            val response = api.getBerryDetail(url)
            response
        } catch (ex: Exception) {
            null
        }
    }
}