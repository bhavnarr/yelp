package com.bnarra.yelp.remote.service

import com.bnarra.yelp.remote.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListingsApi {
    @GET("/v3/businesses/search")
    suspend fun searchPopularBusinessesFromLocationName(
        @Query("location") location: String): Response<ResponseModel>

    @GET("/v3/businesses/search")
    suspend fun searchPopularBusinessesFromLatLong(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double): Response<ResponseModel>

    @GET("/v3/businesses/search")
    suspend fun searchBusinessesFromLatLong(
        @Query("term") searchString: String,
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double): Response<ResponseModel>

    @GET("/v3/businesses/search")
    suspend fun searchBusinessesFromLocationName(
        @Query("term") searchString: String,
        @Query("location") location: String): Response<ResponseModel>
}