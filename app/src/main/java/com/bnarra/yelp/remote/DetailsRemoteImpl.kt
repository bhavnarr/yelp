package com.bnarra.yelp.remote

import com.bnarra.yelp.remote.model.BusinessModel
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.remote.service.DetailsApi
import retrofit2.Response

class DetailsRemoteImpl(private val provider: RetorfitProvider): DetailsRemote {
    override suspend fun details(id: String): BusinessModel? {
        val response: Response<BusinessModel> = provider.retrofit().create(DetailsApi::class.java).details(id)

        return if(response.isSuccessful) response.body() else null
    }
}