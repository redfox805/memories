package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.PhotoResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable {
    companion object {
        fun importFromPhotoResponse(
            photoResponse: PhotoResponse
        ): Photo =
            Photo(
                albumId = photoResponse.albumId,
                id = photoResponse.id,
                title = photoResponse.title,
                url = photoResponse.url,
                thumbnailUrl = photoResponse.thumbnailUrl
            )
    }
}