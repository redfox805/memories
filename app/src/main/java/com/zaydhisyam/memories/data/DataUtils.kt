package com.zaydhisyam.memories.data

import com.zaydhisyam.memories.data.source.remote.response_classes.PostResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.UserResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse

object DataUtils {

    fun createCustomPostList(
        userList: List<UserResponse>,
        postList: List<PostResponse>
    ): List<CustomPostResponse> {
        val userMap = hashMapOf<Int, UserResponse>()

        for (user in userList) {
            userMap[user.id] = user
        }

        val customPostList = arrayListOf<CustomPostResponse>()

        for (post in postList) {
            val customPost = CustomPostResponse(
                userId = post.userId,
                id = post.id,
                title = post.title,
                body = post.body,
                user = userMap[post.userId]!!
            )

            customPostList.add(customPost)
        }

        return customPostList
    }
}