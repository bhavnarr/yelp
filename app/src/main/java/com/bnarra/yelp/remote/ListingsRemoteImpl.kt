package com.bnarra.yelp.remote

import com.bnarra.yelp.remote.model.ResponseModel
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.remote.service.ListingsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ListingsRemoteImpl(private val provider: RetorfitProvider) : ListingsRemote {
    override suspend fun businessesList(name: String?, location: String): ResponseModel? {
        val response: Response<ResponseModel> = if(name.isNullOrEmpty()) {
            provider.retrofit().create(ListingsApi::class.java)
                .searchPopularBusinessesFromLocationName(location)
        } else {
            provider.retrofit().create(ListingsApi::class.java)
                .searchBusinessesFromLocationName(name, location)
        }

        return if(response.isSuccessful) response.body() else null
    }

    override suspend fun businessesListBasedOnLatLng(
        name: String,
        latitude: Double,
        longitude: Double
    ): ResponseModel? {
         val response = provider.retrofit().create(ListingsApi::class.java)
                .searchBusinessesFromLatLong(name, latitude, longitude)

        return if(response.isSuccessful) response.body() else null
    }
}