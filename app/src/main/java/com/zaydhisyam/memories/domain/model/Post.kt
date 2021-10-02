package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val user: User
) : Parcelable {
    companion object {
        fun importFromCustomPostResponse(
            customPostResponse: CustomPostResponse
        ): Post =
            Post(
                userId = customPostResponse.userId,
                id = customPostResponse.id,
                title = customPostResponse.title,
                body = customPostResponse.body,
                user = User.importFromUserResponse(
                    customPostResponse.user
                )
            )
    }
}