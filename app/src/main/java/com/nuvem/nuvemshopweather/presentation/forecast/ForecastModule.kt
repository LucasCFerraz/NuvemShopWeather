package com.nuvem.nuvemshopweather.presentation.forecast

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class ForecastModule {

    @Provides
    internal fun viewModel(activity: ForecastActivity, factory: ViewModelProvider.Factory): ForecastViewModel =
        ViewModelProviders.of(activity, factory).get(ForecastViewModel::class.java)
}