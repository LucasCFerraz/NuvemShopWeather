package com.nuvem.local_provider

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherForecast
import io.reactivex.Single

class WeatherSharedPreferences constructor(private val sharedPreferences: SharedPreferences): WeatherLocalProvider {

    companion object {
        val WEATHER = "WEATHER"
        val WEATHER_FORECAST = "WEATHER_FORECAST"
    }

    override fun setWeatherForCity(city: String, weather: Weather) {
        val weatherJson = GsonBuilder().setPrettyPrinting().create().toJson(weather)
        sharedPreferences
            .edit()
            .putString("($WEATHER)_$city", weatherJson)
            .apply()
    }

    override fun getWeatherForCity(city: String): Single<Weather> {
        try {
            val weather = Gson().fromJson(sharedPreferences.getString(
                "($WEATHER)_$city",
                ""
            ), Weather::class.java)

            return Single.just(weather)
        } catch (exception: Exception) {
            return Single.just(Weather())
        }
    }

    override fun setForecastForCity(city: String, weatherForecast: WeatherForecast) {
        val weatherForecastJson = GsonBuilder().setPrettyPrinting().create().toJson(weatherForecast)
        sharedPreferences
            .edit()
            .putString("($WEATHER_FORECAST)_$city", weatherForecastJson)
            .apply()
    }

    override fun getWeatherForecastForCity(city: String): Single<WeatherForecast> {
        try {
            val weatherForecast = Gson().fromJson(sharedPreferences.getString(
                "($WEATHER_FORECAST)_$city",
                ""
            ), WeatherForecast::class.java)

            return Single.just(weatherForecast)
        } catch (exception: Exception) {
            return Single.just(WeatherForecast())
        }
    }
}