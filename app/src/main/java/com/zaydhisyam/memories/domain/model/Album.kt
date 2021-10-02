package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomAlbumResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val userId: Int,
    val id: Int,
    val title: String,
    val thumbnailUrl: String
) : Parcelable {
    companion object {
        fun importFromCustomAlbumResponse(
            customAlbumResponse: CustomAlbumResponse
        ): Album =
            Album(
                userId = customAlbumResponse.userId,
                id = customAlbumResponse.id,
                title = customAlbumResponse.title,
                thumbnailUrl = customAlbumResponse.thumbnailUrl
            )
    }
}