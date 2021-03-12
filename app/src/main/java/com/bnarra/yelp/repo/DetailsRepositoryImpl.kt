package com.bnarra.yelp.repo

import com.bnarra.yelp.remote.model.BusinessModel

class DetailsRepositoryImpl(private val dataStoreFactory: DetailsDataStoreFactory) : DetailsRepository {
    override suspend fun details(id: String): BusinessModel? = dataStoreFactory.remote.details(id)
}