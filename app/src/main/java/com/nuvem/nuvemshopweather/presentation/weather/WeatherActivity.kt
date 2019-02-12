package com.nuvem.nuvemshopweather.presentation.weather

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nuvem.nuvemshopweather.R
import com.nuvem.nuvemshopweather.databinding.ActivityWeatherBinding
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
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.get().cleanResources()
    }
}
