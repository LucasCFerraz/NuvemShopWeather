package com.nuvem.nuvemshopweather.infra

import android.databinding.BindingAdapter
import android.widget.TextView

class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("android:text")
        fun setText(view: TextView, textId: Int) {
            if(textId == 0)
                return
            view.setText(textId)
        }
    }
}