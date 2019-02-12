package com.nuvem.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nuvem.api_client.weather.WeatherClient
import com.nuvem.api_client.weather.WeatherResponseAPI
import com.nuvem.data.weather.WeatherRepository
import io.reactivex.Single
import org.junit.Test

class WeatherRepositoryTest {

    var subject: WeatherRepository? = null

    @Test
    fun `when weather is called, repository should call getCurrentWeather from client using correct Id and City`() {
        val mock = mock<WeatherClient> {
            on { getCurrentWeather(any(), any()) } doReturn Single.just(WeatherResponseAPI(any(), any()))
        }

        subject = WeatherRepository(mock)

        subject?.getWeatherFromCity("test")

        verify(mock).getCurrentWeather("test", "2cebe458561b8e39965d632395a2852e")
    }
}