package com.bnarra.yelp.remote.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetorfitProvider {
    private val gson = GsonBuilder().setLenient().create()

    internal fun retrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor {
            chain ->
            chain.proceed(chain.request().newBuilder().addHeader(
                "authorization",
                "Bearer pC2hXN7Dpg4SkIUwgWv1M1M4VZhB9mFTxzZmHqKMqQatNBi31gHudC7DYuMbjNKVnw6XYLzcUGP00Cv1DWnDMcXq7W2fBsVP3qnA49xEATKBrb0G26FbOOOy9JtDYHYx").build())
        }

        return Retrofit.Builder()
            .baseUrl("https://api.yelp.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }
}