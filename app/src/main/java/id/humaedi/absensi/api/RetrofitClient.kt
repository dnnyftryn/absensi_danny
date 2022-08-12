package id.humaedi.absensi.api

import androidx.viewbinding.BuildConfig
import id.humaedi.absensi.App.Companion.context
import id.humaedi.absensi.data.LoginData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private lateinit var pref: LoginData
    init {
        pref = LoginData(context)
    }
    var token: String = pref.getToken()
    val interceptor = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private const val BASE_URL = "http://10.0.2.2:8000/api/"
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept","application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: RequestApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(RequestApi::class.java)
    }
}