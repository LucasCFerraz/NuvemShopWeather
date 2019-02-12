package com.nuvem.api_client.weather

import com.google.gson.annotations.SerializedName

data class WeatherDescriptionDetailResponseAPI(

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("main")
    val condition: String? = null
)