package com.benboonya.pokemoninfo.berries.model

import com.google.gson.annotations.SerializedName

data class Berry(
    @SerializedName("name") val name: String,
    @SerializedName("growth_time") val growthTime: Int,
    @SerializedName("max_harvest") val maxHarvest: Int,
    @SerializedName("size") val size: Int
)
