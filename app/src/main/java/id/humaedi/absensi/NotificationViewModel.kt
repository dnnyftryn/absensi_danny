package id.humaedi.absensi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import id.humaedi.absensi.api.ApiConfig
import id.humaedi.absensi.data.DataNotification
import id.humaedi.absensi.helper.NotificationHelper
import org.json.JSONArray

class NotificationViewModel: ViewModel() {
    private var _listNotification = MutableLiveData<ArrayList<DataNotification>>()

    val listNotification : LiveData<ArrayList<DataNotification>> = _listNotification

    fun setNotification() {
        ApiConfig.getListNotification(object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                try {
//                    val detailObject = responseObject.getJSONObject("data")
                    val responseObject = JSONArray(result)
                    _listNotification.postValue(NotificationHelper.listCarouselResponse(responseObject))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }
}