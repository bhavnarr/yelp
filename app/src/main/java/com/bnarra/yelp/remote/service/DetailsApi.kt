package com.bnarra.yelp.remote.service

import com.bnarra.yelp.remote.model.BusinessModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {
    @GET("/v3/businesses/{id}")
    suspend fun details(@Path("id") id: String): Response<BusinessModel>
}