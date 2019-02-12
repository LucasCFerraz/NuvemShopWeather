package com.nuvem.api_client.weather

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherClient {

    @GET("/data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") cityInfo: String,
        @Query("APPID") id: String
    ): Single<WeatherResponseAPI>

    @GET("/data/2.5/forecast")
    fun getForecast(
        @Query("q") cityInfo: String,
        @Query("APPID") id: String
    ): Single<WeatherForecastResponseAPI>
}