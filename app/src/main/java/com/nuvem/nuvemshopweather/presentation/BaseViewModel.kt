package com.nuvem.nuvemshopweather.presentation

import android.arch.lifecycle.ViewModel
import com.nuvem.data.weather.error.HttpCallFailureException
import com.nuvem.data.weather.error.NoNetworkException
import com.nuvem.data.weather.error.ServerUnreachableException
import com.nuvem.data.weather.error.UnknowNetworkException
import com.nuvem.nuvemshopweather.R

open class BaseViewModel: ViewModel() {

    /**
     * This method process the basic errors,
     * if you need to map another error you can override this at the ViewModel.
     * @param error the error
     * @return an int that represents the string in strings.xml
     */
    fun errorMessage(error: Throwable): Int =
        when (error) {
            is NoNetworkException -> R.string.no_network
            is ServerUnreachableException -> R.string.server_unreacheable
            is HttpCallFailureException -> R.string.unknow_error
            is UnknowNetworkException -> R.string.unknow_error
            else -> R.string.unknow_error
        }
}