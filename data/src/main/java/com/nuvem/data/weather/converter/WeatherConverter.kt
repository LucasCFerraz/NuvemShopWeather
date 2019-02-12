package com.nuvem.data.weather.converter

import com.nuvem.api_client.weather.WeatherDescriptionDetailResponseAPI
import com.nuvem.api_client.weather.WeatherDetailResponseAPI
import com.nuvem.api_client.weather.WeatherForecastResponseAPI
import com.nuvem.api_client.weather.WeatherResponseAPI
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherDescriptionDetail
import com.nuvem.domain.weather.model.WeatherDetail
import com.nuvem.domain.weather.model.WeatherForecast

fun WeatherDetailResponseAPI.toDomain(): WeatherDetail =
    WeatherDetail(
        temp = this.temp,
        humidity = this.humidity,
        pressure = this.pressure,
        minTemp = this.minTemp,
        maxTemp = this.maxTemp
    )

fun WeatherDescriptionDetailResponseAPI.toDomain(): WeatherDescriptionDetail =
    WeatherDescriptionDetail(
        description = this.description,
        condition = this.condition
    )

fun WeatherResponseAPI.toDomain(): Weather = Weather(
    weatherDetail = this.weatherDetail?.toDomain(),
    weatherDescriptionDetailList = this.weatherDescriptionDetailList?.map { it.toDomain() }
)

fun WeatherForecastResponseAPI.toDomain(): WeatherForecast =
    WeatherForecast(
        weatherDataList = this.weatherDataList.map { it.toDomain() }
    )