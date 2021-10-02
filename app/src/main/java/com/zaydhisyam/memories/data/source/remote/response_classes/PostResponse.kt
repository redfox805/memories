package com.zaydhisyam.memories.data.source.remote.response_classes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)