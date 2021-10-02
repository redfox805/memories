package com.zaydhisyam.memories.data.source.remote.response_classes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserCompanyResponse(
    val name: String,
    val catchPhrase: String,
    val bs: String
)