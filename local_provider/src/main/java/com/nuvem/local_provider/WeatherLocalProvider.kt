package com.nuvem.local_provider

import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherForecast
import io.reactivex.Single


interface WeatherLocalProvider {

    /**
     * Sets Weather to local persistence
     * @param city is used as a key for save local data
     * @param weather is persisted
     */
    fun setWeatherForCity(city: String, weather: Weather)

    /**
     * Gets current weather for city
     * @param city is used as key for get local data
     * @return A single of weather
     */
    fun getWeatherForCity(city: String): Single<Weather>

    /**
     * Sets forecast for city
     * @param city is used as key for save local data
     */
    fun setForecastForCity(city: String, weatherForecast: WeatherForecast)

    /**
     * Gets forecast weather for city
     * @param city is used as key for get local data
     * @return A single of weatherForecast
     */
    fun getWeatherForecastForCity(city: String): Single<WeatherForecast>
}