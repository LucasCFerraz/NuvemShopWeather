package com.nuvem.nuvemshopweather.infra

import com.nuvem.api_client.infra.ApiClientModule
import com.nuvem.data.weather.di.WeatherDataModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApiClientModule::class,
    ContributorsModule::class,
    ViewModelModule::class,
    WeatherDataModule::class
])

interface ApplicationComponent : AndroidInjector<WeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherApplication>() {
        abstract override fun build(): ApplicationComponent
    }
}