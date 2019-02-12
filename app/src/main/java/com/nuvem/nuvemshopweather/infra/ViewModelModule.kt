package com.nuvem.nuvemshopweather.infra

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nuvem.nuvemshopweather.presentation.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel
}