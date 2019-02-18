package com.nuvem.nuvemshopweather.presentation.forecast

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import com.nuvem.domain.weather.Response
import com.nuvem.domain.weather.usecase.GetForecastWeatherFromCityUseCase
import com.nuvem.nuvemshopweather.BR
import com.nuvem.nuvemshopweather.R
import com.nuvem.nuvemshopweather.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

class ForecastViewModel
@Inject constructor(
    private val getForecastWeatherFromCityUseCase: GetForecastWeatherFromCityUseCase
) : BaseViewModel() {

    private val disposables = CompositeDisposable()

    private val _weatherForecastResponse = MutableLiveData<Response<Unit>>()
    val weatherForecastResponse: LiveData<Response<Unit>>
        get() = _weatherForecastResponse

    val items = ObservableArrayList<ForecastItemViewModel>()
    val itemBinding: ItemBinding<ForecastItemViewModel> = ItemBinding.of(BR.viewModel, R.layout.view_item_forecast)

    fun loadForecast(city: String) {
        getForecastWeatherFromCityUseCase.execute(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _weatherForecastResponse.value = Response.loading() }
            .toObservable()
            .map {
                it.data?.weatherDataList
            }
            .flatMapIterable { item -> item }
            .map {
                ForecastItemViewModel(
                    it.weatherDescriptionDetailList?.get(0)?.description,
                    it.weatherDetail?.maxTemp.toString(),
                    it.weatherDetail?.minTemp.toString(),
                    it.dateTxt
                )
            }
            .toList()
            .subscribe({
                items.clear()
                items.addAll(it)
                _weatherForecastResponse.value = Response.success()
            }, {
                _weatherForecastResponse.value = Response.error(it, errorMessage(it))
            }).addTo(disposables)
    }

    fun cleanResources() =
        disposables.clear()
}