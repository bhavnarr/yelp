package com.bnarra.yelp.repo

import com.bnarra.yelp.remote.ListingsRemote
import com.bnarra.yelp.remote.ListingsRemoteImpl
import com.bnarra.yelp.remote.retrofit.RetorfitProvider

class ListingsDataStoreFactory(
    val remote: ListingsRemote
//    val cache: ListingsCache if caching is supported
) {
    fun remote(): ListingsRemote = ListingsRemoteImpl(RetorfitProvider())

    //implement if caching needs to be supported
    fun cache() = null

}