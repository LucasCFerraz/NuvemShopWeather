package com.nuvem.nuvemshopweather.presentation.forecast

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nuvem.nuvemshopweather.R
import com.nuvem.nuvemshopweather.databinding.ActivityForecastBinding
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ForecastActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: Lazy<ForecastViewModel>

    private lateinit var binding: ActivityForecastBinding

    lateinit var city: String

    companion object {
        val CITY = "CITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.get()
        if(savedInstanceState == null) {
            readExtras()
            viewModel.get().loadForecast(city)
        }
    }

    private fun readExtras() {
        city = intent.getStringExtra(CITY)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.get().cleanResources()
    }
}