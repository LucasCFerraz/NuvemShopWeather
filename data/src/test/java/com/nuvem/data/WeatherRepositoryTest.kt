package com.nuvem.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nuvem.api_client.weather.WeatherClient
import com.nuvem.api_client.weather.WeatherDetailResponseAPI
import com.nuvem.api_client.weather.WeatherForecastResponseAPI
import com.nuvem.api_client.weather.WeatherResponseAPI
import com.nuvem.data.weather.WeatherRepository
import com.nuvem.data.weather.error.NoNetworkException
import com.nuvem.domain.weather.model.Weather
import com.nuvem.domain.weather.model.WeatherDetail
import com.nuvem.domain.weather.model.WeatherForecast
import com.nuvem.local_provider.WeatherLocalProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class WeatherRepositoryTest {

    lateinit var subject: WeatherRepository

    lateinit var mockWeatherClient: WeatherClient
    lateinit var mockWeatherLocalProvider: WeatherLocalProvider

    private val testWeatherAPI = WeatherResponseAPI(
        WeatherDetailResponseAPI(
        1f,
        null,
        null,
        null,
        null
    ))

    private val testWeather = Weather(
        WeatherDetail(
            1f,
            null,
            null,
            null,
            null
        )
    )

    private val testWeatherForecastAPI = WeatherForecastResponseAPI(
        listOf(testWeatherAPI)
    )

    private val testWeatherForecast = WeatherForecast(listOf(testWeather))

    @Before
    fun setup() {
        mockWeatherClient = mock()
        mockWeatherLocalProvider = mock()
        subject = WeatherRepository(mockWeatherClient, mockWeatherLocalProvider)
    }

    @Test
    fun `when currentWeather is called, repository should call getCurrentWeather from client using correct Id and City`() {
        whenever(mockWeatherClient.getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.just(testWeatherAPI))

        subject.getWeatherFromCity("test")

        verify(mockWeatherClient).getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e")
    }

    @Test
    fun `when currentWeather is called, and client return success, setWeather need to be called`() {
        whenever(mockWeatherClient.getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.just(testWeatherAPI))

        subject.getWeatherFromCity("test").test()

        verify(mockWeatherLocalProvider).setWeatherForCity("test", testWeather)
    }

    @Test
    fun `when currentWeather is called, and client return success, the method needs to return a Weather`() {
        whenever(mockWeatherClient.getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.just(testWeatherAPI))

        subject.getWeatherFromCity("test")
            .test()
            .assertValue { it.isSuccess() && it.data!!.equals(testWeather) }
    }

    @Test
    fun `when currentWeather is called, and client throws error, getWeather from provider need to be called`() {
        whenever(mockWeatherClient.getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.error(SocketTimeoutException()))

        subject.getWeatherFromCity("test").test()

        verify(mockWeatherLocalProvider).getWeatherForCity("test")
    }

    @Test
    fun `when currentWeather is called, and client throws error, the exception should be changed if local data not available`() {
        whenever(mockWeatherClient.getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.error(SocketTimeoutException()))

        whenever(mockWeatherLocalProvider.getWeatherForCity("test"))
            .thenReturn(Single.just(Weather()))

        subject.getWeatherFromCity("test")
            .test()
            .assertError {
                it is NoNetworkException
            }
    }

    @Test
    fun `when forecastWeather is called, repository should call getForecast from client using correct Id and City`() {
        whenever(mockWeatherClient.getForecast("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.just(testWeatherForecastAPI))

        subject.getForecast("test")

        verify(mockWeatherClient).getForecast("test", "2cebe458561b8e39965d632395a2852e")
    }

    @Test
    fun `when forecastWeather is called, and client return success, setForecast need to be called`() {
        whenever(mockWeatherClient.getForecast("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.just(testWeatherForecastAPI))

        subject.getForecast("test").test()

        verify(mockWeatherLocalProvider).setForecastForCity("test", testWeatherForecast)
    }

    @Test
    fun `when forecastWeather is called, and client return success, the method needs to return a WeatherForecast`() {
        whenever(mockWeatherClient.getForecast("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.just(testWeatherForecastAPI))

        subject.getForecast("test")
            .test()
            .assertValue { it.isSuccess() && it.data!!.equals(testWeatherForecast) }
    }

    @Test
    fun `when forecastWeather is called, and client throws error, getForecast from provider need to be called`() {
        whenever(mockWeatherClient.getForecast("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.error(SocketTimeoutException()))

        subject.getForecast("test").test()

        verify(mockWeatherLocalProvider).getWeatherForecastForCity("test")
    }

    @Test
    fun `when forecastWeather is called, and client throws error, the exception should be changed if local data not available`() {
        whenever(mockWeatherClient.getForecast("test", "2cebe458561b8e39965d632395a2852e"))
            .thenReturn(Single.error(SocketTimeoutException()))

        whenever(mockWeatherLocalProvider.getWeatherForecastForCity("test"))
            .thenReturn(Single.just(WeatherForecast()))

        subject.getForecast("test")
            .test()
            .assertError {
                it is NoNetworkException
            }
    }
}