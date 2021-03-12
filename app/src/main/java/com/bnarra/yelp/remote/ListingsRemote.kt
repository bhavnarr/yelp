package com.bnarra.yelp.remote

import com.bnarra.yelp.remote.model.BusinessModel
import com.bnarra.yelp.remote.model.ResponseModel

interface ListingsRemote {
    suspend fun businessesListBasedOnLatLng(name: String, latitude: Double, longitude: Double) : ResponseModel?

    suspend fun businessesList(name: String?, location: String) : ResponseModel?
}