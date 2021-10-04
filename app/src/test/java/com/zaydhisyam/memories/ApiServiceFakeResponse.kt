package com.zaydhisyam.memories

import com.zaydhisyam.memories.data.source.remote.response_classes.*

object ApiServiceFakeResponse {

    fun createUserResponse(i: Int): UserResponse =
        UserResponse(
            id = i,
            name = "name_$i",
            email = "email_$i",
            address = createUserAddressResponse(i),
            company = createUserCompanyResponse(i)
        )

    private fun createUserAddressResponse(i: Int): UserAddressResponse =
        UserAddressResponse(
            street = "street_$i",
            suite = "suite_$i",
            city = "city_$i",
            zipcode = "zipcode_$i",
            geo = createUserAddressGeoResponse(i)
        )

    private fun createUserAddressGeoResponse(i: Int): UserAddressGeoResponse =
        UserAddressGeoResponse(
            lat = "lat_$i",
            lng = "lng_$i"
        )

    private fun createUserCompanyResponse(i: Int): UserCompanyResponse =
        UserCompanyResponse(
            name = "name_$i",
            catchPhrase = "catch_phrase_$i",
            bs = "bs_$i"
        )

    fun createPostResponse(i: Int): PostResponse =
        PostResponse(
            userId = i,
            id = i,
            title = "title_$i",
            body = "body_$i"
        )

    fun createCommentResponse(i: Int): CommentResponse =
        CommentResponse(
            postId = i,
            id = i,
            name = "name_$i",
            email = "email_$i",
            body = "body_$i"
        )

    fun createAlbumResponse(i: Int): AlbumResponse =
        AlbumResponse(
            userId = i,
            id = i,
            title = "title_$i"
        )

    fun createPhotoResponse(i: Int): PhotoResponse =
        PhotoResponse(
            albumId = i,
            id = i,
            title = "title_$i",
            url = "url_$i",
            thumbnailUrl = "thumbnail_url_$i"
        )
}