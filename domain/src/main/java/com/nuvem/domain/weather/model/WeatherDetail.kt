package com.nuvem.domain.weather.model

data class WeatherDetail (
    val temp: Float? = null,
    val humidity: Float? = null,
    val pressure: Float? = null,
    val minTemp: Float? = null,
    val maxTemp: Float? = null
)