package com.zaydhisyam.memories

data class UserModel(
    val userUsername: String,
    val userName: String,
    val userAvatar: String,
    val userCompany: String,
    val userLocation: String,
    val userRepositorySize: Int,
    val userFollowerSize: Int,
    val userFollowingSize: Int
)
