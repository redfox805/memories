package com.zaydhisyam.memories.data.source.remote.response_classes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAddressGeoResponse(
    val lat: String,
    val lng: String
)