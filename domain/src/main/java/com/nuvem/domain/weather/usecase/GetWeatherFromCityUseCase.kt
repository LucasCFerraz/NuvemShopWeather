package com.nuvem.domain.weather.usecase

import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.domain.weather.model.Weather
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherFromCityUseCase
@Inject constructor(private val weatherRepositoryContract: WeatherRepositoryContract
) {

    fun execute(city: String): Single<Response<Weather>> =
        weatherRepositoryContract.getWeatherFromCity(city)
}