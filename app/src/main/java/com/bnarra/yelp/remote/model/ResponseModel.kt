package com.bnarra.yelp.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("businesses") val businesses: List<BusinessModel>
)