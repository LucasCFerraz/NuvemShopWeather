package com.nuvem.nuvemshopweather.presentation.weather


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    internal fun viewModel(activity: WeatherActivity, factory: ViewModelProvider.Factory): WeatherViewModel =
        ViewModelProviders.of(activity, factory).get(WeatherViewModel::class.java)
}