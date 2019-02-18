package com.nuvem.nuvemshopweather.presentation.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.databinding.ObservableField
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.usecase.GetWeatherFromCityUseCase
import com.nuvem.domain.weather.model.Weather
import com.nuvem.nuvemshopweather.presentation.BaseViewModel
import com.nuvem.nuvemshopweather.presentation.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel
@Inject constructor(private val getWeatherUseCase: GetWeatherFromCityUseCase): BaseViewModel() {

    val cities = arrayOf(
        "Silverstone, UK",
        "São Paulo, Brazil",
        "Melbourne, Australia",
        "Monte Carlo, Monaco"
    )

    private val _weatherResponse = MutableLiveData<Response<Weather>>()
    val weatherResponse: LiveData<Response<Weather>>
        get() = _weatherResponse

    val citySelectionResponse = SingleLiveEvent<String>()

    private val disposables = CompositeDisposable()

    var citySelectionIndex = -1
        set(value) {
            if(value == field)
                return
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
                _weatherResponse.value = Response.error(it, errorMessage(it))
            }).addTo(disposables)
    }

    fun clickDetails() {
        citySelectionResponse.value = cities.get(citySelectionIndex)
    }

    fun cleanResources() {
        disposables.clear()
    }

}