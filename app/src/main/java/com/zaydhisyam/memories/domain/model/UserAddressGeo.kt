package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.UserAddressGeoResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAddressGeo(
    val lat: String,
    val lng: String
) : Parcelable {
    companion object {
        fun importFromUserAddressGeoResponse(
            userAddressGeoResponse: UserAddressGeoResponse
        ): UserAddressGeo =
            UserAddressGeo(
                lat = userAddressGeoResponse.lat,
                lng = userAddressGeoResponse.lng
            )
    }
}
