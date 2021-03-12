package com.bnarra.yelp.repo

import com.bnarra.yelp.remote.DetailsRemote

class DetailsDataStoreFactory(
    val remote: DetailsRemote
//    val cache: DetailsCache
) {
    fun remote() = remote

    fun cache() = null
}