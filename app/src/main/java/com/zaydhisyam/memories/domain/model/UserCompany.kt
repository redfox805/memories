package com.zaydhisyam.memories.domain.model

import android.os.Parcelable
import com.zaydhisyam.memories.data.source.remote.response_classes.UserCompanyResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserCompany(
    val name: String,
    val catchPhrase: String,
    val bs: String
) : Parcelable {
    companion object {
        fun importFromUserCompanyResponse(
            userCompanyResponse: UserCompanyResponse
        ): UserCompany =
            UserCompany(
                name = userCompanyResponse.name,
                catchPhrase = userCompanyResponse.catchPhrase,
                bs = userCompanyResponse.bs
            )
    }
}