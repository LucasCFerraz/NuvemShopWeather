package com.nuvem.nuvemshopweather.presentation.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.databinding.ObservableField
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.usecase.GetWeatherFromCityUseCase
import com.nuvem.domain.weather.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel
@Inject constructor(private val getWeatherUseCase: GetWeatherFromCityUseCase): ViewModel() {

    val cities = arrayOf(
        "Silverstone, UK",
        "São Paulo, Brazil",
        "Melbourne, Australia",
        "Monte Carlo, Monaco"
    )

    private val _weatherResponse = MutableLiveData<Response<Weather>>()
    val weatherResponse: LiveData<Response<Weather>>
        get() = _weatherResponse

    private val disposables = CompositeDisposable()

    var citySelectionIndex = 0
        get() = field
        set(value) {
            field = value
            getCurrentWeatherForCity(cities.get(field))
        }

    private fun getCurrentWeatherForCity(city: String) {
        getWeatherUseCase.execute(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _weatherResponse.value = Response.loading() }
            .subscribe({
                _weatherResponse.value = it
            },{
                _weatherResponse.value = Response.error(it,"Connection Error")
            }).addTo(disposables)
    }

    fun getForecastForCity(city: String) {

    }

    fun clickDetails() {

    }

    fun cleanResources() {
        disposables.clear()
    }

}