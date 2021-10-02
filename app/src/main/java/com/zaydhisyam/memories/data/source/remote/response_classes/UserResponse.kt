package com.zaydhisyam.memories.data.source.remote.response_classes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val address: UserAddressResponse,
    val company: UserCompanyResponse
)