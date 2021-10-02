package com.zaydhisyam.memories.data.source.remote.response_classes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAddressResponse(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: UserAddressGeoResponse
)