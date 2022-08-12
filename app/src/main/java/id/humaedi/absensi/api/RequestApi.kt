package id.humaedi.absensi.api

import id.humaedi.absensi.data.LoginResponse
import id.humaedi.absensi.data.RegistResponse
import retrofit2.Call
import retrofit2.http.*

interface RequestApi {
    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("regist")
    fun userRegist(
        @Field("first_name") namaPertama: String,
        @Field("last_name") namaKedua: String,
        @Field("tgl_lahir") tanggalLahir: String,
        @Field("phone") nomorTelepon: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<RegistResponse>

    @POST("logout")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    fun userLogout(
            @Header("Authorization") token: String
        ): Call<RegistResponse>
}