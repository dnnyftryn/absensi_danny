package id.humaedi.absensi.api.testdummy

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestHandle
import id.humaedi.absensi.api.RetrofitClient.token

class ApiConfigDummy {
    companion object {
        private const val BASE_URL2 = "https://qzg3g.mocklab.io/"
        private const val DEFAULT_TIMEOUT = 20 * 10000

        private fun getClient(): AsyncHttpClient {
            val client = AsyncHttpClient()
            client.addHeader("Accept", "application/json")
            client.setTimeout(DEFAULT_TIMEOUT)
            return client
        }


        fun getListAbsensi(responseHandler: AsyncHttpResponseHandler): RequestHandle? {
            val url = "$BASE_URL2/daftarabsensi"
            return getClient().get(url, responseHandler)
        }

    }
}