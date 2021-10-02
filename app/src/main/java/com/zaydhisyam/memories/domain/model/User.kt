package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val address: UserAddress,
    val company: UserCompany
) : Parcelable {
    companion object {
        fun importFromUserResponse(userResponse: UserResponse): User =
            User(
                id = userResponse.id,
                name = userResponse.name,
                email = userResponse.email,
                address = UserAddress.importFromUserAddressResponse(
                    userResponse.address
                ),
                company = UserCompany.importFromUserCompanyResponse(
                    userResponse.company
                )
            )
    }
}
