package com.nuvem.api_client.weather

import com.google.gson.annotations.SerializedName

data class WeatherDetailResponseAPI (
    @SerializedName("temp")
    val temp: Float? = null,

    @SerializedName("humidity")
    val humidity: Float? = null,

    @SerializedName("pressure")
    val pressure: Float? = null,

    @SerializedName("temp_min")
    val minTemp: Float? = null,

    @SerializedName("temp_max")
    val maxTemp: Float? = null
)