package com.bnarra.yelp.repo

import com.bnarra.yelp.remote.model.ResponseModel

class ListingsRepositoryImpl(private val dataStoreFactory: ListingsDataStoreFactory) :
    ListingsRepository {
    override suspend fun findBusinessesBasedOnLatLng(
        name: String,
        latitude: Double,
        longitude: Double
    ): ResponseModel? {
        return dataStoreFactory.remote.businessesListBasedOnLatLng(name, latitude, longitude)
    }

    override suspend fun findBusinesses(name: String?, location: String): ResponseModel? {
       return dataStoreFactory.remote.businessesList(name, location)
    }
}