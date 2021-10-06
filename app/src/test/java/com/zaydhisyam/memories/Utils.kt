package com.zaydhisyam.memories

import com.zaydhisyam.memories.data.source.remote.response_classes.*
import okio.buffer
import okio.source
import org.json.JSONArray
import java.nio.charset.StandardCharsets

object Utils {

    private fun readJsonFromFile(jsonFileName: String): JSONArray {
        val inputStream = javaClass.classLoader?.getResourceAsStream(jsonFileName)

        val source = inputStream?.let { inputStream.source().buffer() }

        return JSONArray(source?.readString(StandardCharsets.UTF_8)!!)
    }

    fun getUserResponseListFromJsonFile()
            : List<UserResponse> {
        val jsonResponse = readJsonFromFile("user_response_list.json")

        val userLength = jsonResponse.length()

        val userResponseList = arrayListOf<UserResponse>()

        for (i in 0 until userLength) {
            val userJson = jsonResponse.getJSONObject(i)

            val addressJson = userJson.getJSONObject("address")
            val geoJson = addressJson.getJSONObject("geo")
            val companyJson = userJson.getJSONObject("company")

            val companyResponse = UserCompanyResponse(
                name = companyJson.getString("name"),
                catchPhrase = companyJson.getString("catchPhrase"),
                bs = companyJson.getString("bs")
            )

            val geoResponse = UserAddressGeoResponse(
                lat = geoJson.getString("lat"),
                lng = geoJson.getString("lng")
            )

            val addressResponse = UserAddressResponse(
                street = addressJson.getString("street"),
                suite = addressJson.getString("suite"),
                city = addressJson.getString("city"),
                zipcode = addressJson.getString("zipcode"),
                geo = geoResponse
            )

            val userResponse = UserResponse(
                id = userJson.getInt("id"),
                name = userJson.getString("name"),
                email = userJson.getString("email"),
                address = addressResponse,
                company = companyResponse
            )

            userResponseList.add(userResponse)
        }

        return userResponseList
    }

    fun getPostResponseListFromJsonFile()
            : List<PostResponse> {
        val jsonResponse = readJsonFromFile("post_response_list.json")

        val postLength = jsonResponse.length()

        val postResponseList = arrayListOf<PostResponse>()

        for (i in 0 until postLength) {
            val postJson = jsonResponse.getJSONObject(i)

            val postResponse = PostResponse(
                userId = postJson.getInt("userId"),
                id = postJson.getInt("id"),
                title = postJson.getString("title"),
                body = postJson.getString("body")
            )

            postResponseList.add(postResponse)
        }

        return postResponseList
    }

    fun getCommentResponsePerPostListFromJsonFile()
            : List<CommentResponse> {
        val jsonResponse = readJsonFromFile("comment_response_list_post_1.json")

        val commentLength = jsonResponse.length()

        val commentResponseList = arrayListOf<CommentResponse>()

        for (i in 0 until commentLength) {
            val commentJson = jsonResponse.getJSONObject(i)

            val commentResponse = CommentResponse(
                postId = commentJson.getInt("postId"),
                id = commentJson.getInt("id"),
                name = commentJson.getString("name"),
                email = commentJson.getString("email"),
                body = commentJson.getString("body")
            )

            commentResponseList.add(commentResponse)
        }

        return commentResponseList
    }

    fun getAlbumResponseListPerUserFromJsonFile()
            : List<AlbumResponse> {
        val jsonResponse = readJsonFromFile("album_response_list_user_1.json")

        val albumLength = jsonResponse.length()

        val albumResponseList = arrayListOf<AlbumResponse>()

        for (i in 0 until albumLength) {
            val albumJson = jsonResponse.getJSONObject(i)

            val albumResponse = AlbumResponse(
                userId = albumJson.getInt("userId"),
                id = albumJson.getInt("id"),
                title = albumJson.getString("title")
            )

            albumResponseList.add(albumResponse)
        }

        return albumResponseList
    }

    fun getPhotoResponseListPerAlbumFromJsonFile()
            : List<PhotoResponse> {
        val jsonResponse = readJsonFromFile("photo_response_list_album_1.json")

        val photoLength = jsonResponse.length()

        val photoResponseList = arrayListOf<PhotoResponse>()

        for (i in 0 until photoLength) {
            val photoJson = jsonResponse.getJSONObject(i)

            val photoResponse = PhotoResponse(
                albumId = photoJson.getInt("albumId"),
                id = photoJson.getInt("id"),
                title = photoJson.getString("title"),
                url = photoJson.getString("url"),
                thumbnailUrl = photoJson.getString("thumbnailUrl")
            )

            photoResponseList.add(photoResponse)
        }

        return photoResponseList
    }
}