package com.nuvem.nuvemshopweather.presentation.weather

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nuvem.domain.weather.Response
import com.nuvem.nuvemshopweather.R
import com.nuvem.nuvemshopweather.databinding.ActivityWeatherBinding
import com.nuvem.nuvemshopweather.presentation.forecast.ForecastActivity
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherActivity: DaggerAppCompatActivity() {

    @Inject lateinit var viewModel: Lazy<WeatherViewModel>

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.get()
        observers()
    }

    private fun observers() {
        viewModel.get().citySelectionResponse.observe(this, Observer {
            val city = it ?: return@Observer
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra(ForecastActivity.CITY, city)
            startActivity(intent)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.get().cleanResources()
    }
}
