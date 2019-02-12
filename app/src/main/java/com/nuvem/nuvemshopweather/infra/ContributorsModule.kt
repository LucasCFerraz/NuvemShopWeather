package com.nuvem.nuvemshopweather.infra

import com.nuvem.nuvemshopweather.presentation.weather.WeatherActivity
import com.nuvem.nuvemshopweather.presentation.weather.WeatherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ContributorsModule {

    @ContributesAndroidInjector(modules = [WeatherModule::class])
    internal abstract fun weatherActivity(): WeatherActivity
}