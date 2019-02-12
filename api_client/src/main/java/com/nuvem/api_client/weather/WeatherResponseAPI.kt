package com.nuvem.api_client.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponseAPI (
    @SerializedName("main")
    val weatherDetail: WeatherDetailResponseAPI? = null,

    @SerializedName("weather")
    val weatherDescriptionDetailList: List<WeatherDescriptionDetailResponseAPI>? = null
)