package com.nuvem.nuvemshopweather.infra

import android.content.Context
import com.nuvem.local_provider.WeatherLocalProvider
import com.nuvem.local_provider.WeatherSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ApplicationModule {

    companion object {
        val PREF_NAME = "WEATHER"
    }

    @Provides
    fun context(application: WeatherApplication): Context =
        application

    @Provides
    @Singleton
    fun weatherLocalProvider(context: Context) : WeatherLocalProvider {
        return WeatherSharedPreferences(context.getSharedPreferences(PREF_NAME, 0))
    }
}