package com.nuvem.data.weather

import com.nuvem.api_client.weather.WeatherClient
import com.nuvem.data.weather.converter.toDomain
import com.nuvem.data.weather.error.mapNetworkError
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.domain.weather.model.WeatherForecast
import com.nuvem.local_provider.WeatherLocalProvider
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository
@Inject constructor(
    private val clientWeather: WeatherClient,
    private val weatherLocalProvider: WeatherLocalProvider
) : WeatherRepositoryContract {

    override fun getForecast(city: String): Single<Response<WeatherForecast>> =
        clientWeather.getForecast(city, "2cebe458561b8e39965d632395a2852e")
            .map {
                val weather = it.toDomain()
                weatherLocalProvider.setForecastForCity(city, weather)
                weather
            }
            .onErrorResumeNext { throwable ->
                weatherLocalProvider.getWeatherForecastForCity(city)
                    .map {
                        if (it.weatherDataList == null)
                            throw throwable
                        else
                            it
                    }
            }
            .mapNetworkError()
            .map { Response.success(it) }

    override fun getWeatherFromCity(city: String): Single<Response<Weather>> =
        clientWeather.getCurrentWeather(city, "2cebe458561b8e39965d632395a2852e")
            .map {
                val weather = it.toDomain()
                weatherLocalProvider.setWeatherForCity(city, weather)
                weather
            }
            .onErrorResumeNext { throwable ->
                weatherLocalProvider.getWeatherForCity(city)
                    .map {
                        if (it.weatherDetail == null)
                            throw throwable
                        else
                            it
                    }
            }
            .mapNetworkError()
            .map { Response.success(it) }
}