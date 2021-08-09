package com.benboonya.pokemoninfo.common

const val POKEMON_DETAIL = "pokemonDetail/{url}"
fun getPokemonDetailRoute(url: String) = "pokemonDetail/$url"
const val BERRY_DETAIL = "berryDetail/{url}"
fun getBerryDetailRoute(url: String) = "berryDetail/$url"