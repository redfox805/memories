package com.zaydhisyam.memories.domain.model

import com.zaydhisyam.memories.data.source.remote.response_classes.CommentResponse

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) {
    companion object {
        fun importFromCommentResponse(commentResponse: CommentResponse): Comment =
            Comment(
                postId = commentResponse.postId,
                id = commentResponse.id,
                name = commentResponse.name,
                email = commentResponse.email,
                body = commentResponse.body
            )
    }
}