package com.bnarra.yelp.repo

import com.bnarra.yelp.remote.model.BusinessModel

interface DetailsRepository {
    suspend fun details(id: String): BusinessModel?
}