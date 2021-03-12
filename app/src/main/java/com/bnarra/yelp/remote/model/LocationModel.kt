package com.bnarra.yelp.remote.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("display_address") val displayAddress: List<String>
)