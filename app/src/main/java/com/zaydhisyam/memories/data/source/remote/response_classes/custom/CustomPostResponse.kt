package com.zaydhisyam.memories.data.source.remote.response_classes.custom

import com.zaydhisyam.memories.data.source.remote.response_classes.UserResponse

data class CustomPostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val user: UserResponse
)
