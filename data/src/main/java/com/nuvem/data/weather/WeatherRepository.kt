package com.nuvem.data.weather

import com.nuvem.api_client.weather.WeatherClient
import com.nuvem.data.weather.converter.toDomain
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.domain.weather.model.WeatherForecast
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val clientWeather: WeatherClient) :
    WeatherRepositoryContract {

    override fun getForecast(city: String): Single<Response<WeatherForecast>> =
        clientWeather.getForecast(city, "2cebe458561b8e39965d632395a2852e")
            .map { Response.success(it.toDomain()) }

    override fun getWeatherFromCity(city: String): Single<Response<Weather>> =
        clientWeather.getCurrentWeather(city, "2cebe458561b8e39965d632395a2852e")
            .map { Response.success(it.toDomain()) }
}