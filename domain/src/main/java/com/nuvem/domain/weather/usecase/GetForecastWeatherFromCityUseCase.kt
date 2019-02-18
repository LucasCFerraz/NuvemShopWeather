package com.nuvem.domain.weather.usecase

import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.domain.weather.model.WeatherForecast
import io.reactivex.Single
import javax.inject.Inject

class GetForecastWeatherFromCityUseCase
@Inject constructor(private val weatherRepositoryContract: WeatherRepositoryContract
) {
    fun execute(city: String): Single<Response<WeatherForecast>> =
        weatherRepositoryContract.getForecast(city)
}