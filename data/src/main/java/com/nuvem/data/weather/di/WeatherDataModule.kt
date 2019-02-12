package com.nuvem.data.weather.di

import com.nuvem.data.weather.WeatherRepository
import com.nuvem.domain.weather.WeatherRepositoryContract
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherDataModule {

    @Binds
    abstract fun bindWeatherRepository(repository: WeatherRepository): WeatherRepositoryContract
}