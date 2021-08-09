package com.benboonya.pokemoninfo.pokemon.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int
) {
    val heightInMetre: Float
        get() = height.toFloat() / 10

    val weightInKilogram: Float
        get() = weight.toFloat() / 10

    val displayExp: String
        get() = baseExperience.toString()
}


