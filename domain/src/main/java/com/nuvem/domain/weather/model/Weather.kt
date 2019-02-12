package com.nuvem.domain.weather.model

data class Weather (
    val weatherDetail: WeatherDetail? = null,
    val weatherDescriptionDetailList: List<WeatherDescriptionDetail>? = null
)