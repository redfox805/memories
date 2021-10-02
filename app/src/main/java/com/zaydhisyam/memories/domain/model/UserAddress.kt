package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.UserAddressResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAddress(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: UserAddressGeo
) : Parcelable {
    companion object {
        fun importFromUserAddressResponse(
            userAddressResponse: UserAddressResponse
        ): UserAddress =
            UserAddress(
                street = userAddressResponse.street,
                suite = userAddressResponse.suite,
                city = userAddressResponse.city,
                zipcode = userAddressResponse.zipcode,
                geo = UserAddressGeo.importFromUserAddressGeoResponse(
                    userAddressResponse.geo
                )
            )
    }
}