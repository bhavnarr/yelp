package com.bnarra.yelp.repo

import com.bnarra.yelp.remote.model.ResponseModel

interface ListingsRepository {
    suspend fun findBusinessesBasedOnLatLng(name: String, latitude: Double, longitude: Double) : ResponseModel?

    suspend fun findBusinesses(name: String?, location: String): ResponseModel?

}