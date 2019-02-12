package com.nuvem.api_client.infra

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nuvem.api_client.weather.WeatherClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiClientModule {

    @Provides
    @Singleton
    internal fun gson(): Gson =
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setExclusionStrategies(IgnoreNonSerializedNameFields.create())
            .create()


    @Provides
    @Singleton
    internal fun retrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    internal fun weatherClient(retrofit: Retrofit): WeatherClient =
            retrofit.create(WeatherClient::class.java)

}