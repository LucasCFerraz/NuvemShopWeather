package com.nuvem.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherDetail
import com.nuvem.domain.weather.usecase.GetWeatherFromCityUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetWeatherFromCityUseCaseTest {

    lateinit var mockWeatherRepository: WeatherRepositoryContract

    lateinit var subject: GetWeatherFromCityUseCase

    private val testString = "testString"
    private val testWeather = Weather(WeatherDetail(
        1f,
        null,
        null,
        null,
        null
    ))

    private val testWeatherResponse = Response.success(testWeather)

    @Before
    fun setup() {
        mockWeatherRepository = mock()
        subject = GetWeatherFromCityUseCase(mockWeatherRepository)
    }

    @Test
    fun `when execute method are called, the method getWeatherFromCity should be called`() {
        subject.execute(testString)
        verify(mockWeatherRepository).getWeatherFromCity(testString)
    }

    @Test
    fun `when execute method are called, the method should return a Single Weather`() {
        whenever(mockWeatherRepository.getWeatherFromCity(testString))
            .thenReturn(
                Single.just(testWeatherResponse)
            )

        subject.execute(testString)
            .test()
            .assertResult(testWeatherResponse)
    }

    @Test
    fun `when execute method are called and any exception happens in repository layer, execute should return a Response error`() {
        whenever(mockWeatherRepository.getWeatherFromCity(testString))
            .thenReturn(
                Single.error(Throwable())
            )

        subject.execute(testString)
            .test()
            .assertError(Throwable::class.java)

    }
}