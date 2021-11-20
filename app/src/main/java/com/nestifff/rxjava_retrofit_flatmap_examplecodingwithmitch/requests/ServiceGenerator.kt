package com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.requests

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    private val requestsApi = retrofit.create(RequestsApi::class.java)

    fun getRequestsApi(): RequestsApi = requestsApi
}

const val BASE_URL = "https://jsonplaceholder.typicode.com"
