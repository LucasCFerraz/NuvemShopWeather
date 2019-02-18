package com.nuvem.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherDetail
import com.nuvem.domain.weather.model.WeatherForecast
import com.nuvem.domain.weather.usecase.GetForecastWeatherFromCityUseCase
import com.nuvem.domain.weather.usecase.GetWeatherFromCityUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetForecastWeatherFromCityUseCaseTest {

    lateinit var mockWeatherRepository: WeatherRepositoryContract

    lateinit var subject: GetForecastWeatherFromCityUseCase

    private val testString = "testString"
    private val testWeather = WeatherForecast(listOf(Weather()))

    private val testWeatherResponse = Response.success(testWeather)

    @Before
    fun setup() {
        mockWeatherRepository = mock()
        subject = GetForecastWeatherFromCityUseCase(mockWeatherRepository)
    }

    @Test
    fun `when execute method are called, the method getForecast should be called`() {
        subject.execute(testString)
        verify(mockWeatherRepository).getForecast(testString)
    }

    @Test
    fun `when execute method are called, the method should return a Single WeatherForecast`() {
        whenever(mockWeatherRepository.getForecast(testString))
            .thenReturn(
                Single.just(testWeatherResponse)
            )

        subject.execute(testString)
            .test()
            .assertResult(testWeatherResponse)
    }

    @Test
    fun `when execute method are called and any exception happens in repository layer, execute should return a Response error`() {
        whenever(mockWeatherRepository.getForecast(testString))
            .thenReturn(
                Single.error(Throwable())
            )

        subject.execute(testString)
            .test()
            .assertError(Throwable::class.java)

    }
}