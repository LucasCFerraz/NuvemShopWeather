package com.nuvem.api_client.weather

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponseAPI(
    @SerializedName("list")
    val weatherDataList: List<WeatherResponseAPI>
)