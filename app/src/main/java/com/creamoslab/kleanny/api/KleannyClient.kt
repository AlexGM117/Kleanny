package com.creamoslab.kleanny.api

import com.creamoslab.kleanny.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object KleannyClient {
    lateinit var clientBuilder : OkHttpClient.Builder
    var loggingInterceptor = HttpLoggingInterceptor()
    var BASE_URL = "https://us-central1-devcreamoslab.cloudfunctions.net/"

    fun getInstance() : KleannyService {
        clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG){
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(clientBuilder.build())
            .build()
            .create(KleannyService::class.java)
    }
}