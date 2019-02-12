package com.nuvem.nuvemshopweather.infra

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
internal class ApplicationModule {

    @Provides
    fun context(application: WeatherApplication): Context =
        application
}