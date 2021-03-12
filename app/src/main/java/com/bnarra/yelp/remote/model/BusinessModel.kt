package com.bnarra.yelp.remote.model

import com.google.gson.annotations.SerializedName

data class BusinessModel(
    @SerializedName("name") val name: String,
    @SerializedName("location") val location: LocationModel,
    @SerializedName("categories") val category: List<CategoryModel>,
    @SerializedName("image_url") val imgUrl: String,
    @SerializedName("id") val id: String
)