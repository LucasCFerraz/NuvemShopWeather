package com.nuvem.nuvemshopweather.infra

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WeatherApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }
}
