package com.bnarra.yelp.remote

import com.bnarra.yelp.remote.model.BusinessModel

interface DetailsRemote {
    suspend fun details(id: String): BusinessModel?
}