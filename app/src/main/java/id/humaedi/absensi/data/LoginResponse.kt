package id.humaedi.absensi.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val user: getUserData,
)

data class getUserData (
    @field:SerializedName("token")
    val token: String,
)
