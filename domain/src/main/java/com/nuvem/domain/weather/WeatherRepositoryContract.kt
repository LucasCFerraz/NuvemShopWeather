package com.nuvem.domain.weather

import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherForecast
import io.reactivex.Single

interface WeatherRepositoryContract {
    fun getWeatherFromCity(city: String): Single<Response<Weather>>
    fun getForecast(city: String): Single<Response<WeatherForecast>>
}