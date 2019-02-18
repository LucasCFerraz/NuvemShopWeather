package com.nuvem.data.weather.di

import com.nuvem.api_client.weather.WeatherClient
import com.nuvem.data.weather.WeatherRepository
import com.nuvem.domain.weather.WeatherRepositoryContract
import com.nuvem.local_provider.WeatherLocalProvider
import dagger.Module
import dagger.Provides

@Module
class WeatherDataModule {

    @Provides
    internal fun weatherRepository(weatherClient: WeatherClient, weatherLocalProvider: WeatherLocalProvider
    ): WeatherRepositoryContract = WeatherRepository(weatherClient, weatherLocalProvider)
}