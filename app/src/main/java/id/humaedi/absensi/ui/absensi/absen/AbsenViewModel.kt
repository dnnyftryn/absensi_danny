package id.humaedi.absensi.ui.absensi.absen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import id.humaedi.absensi.api.testdummy.ApiConfigDummy
import id.humaedi.absensi.api.testdummy.data.DataAbsensi
import id.humaedi.absensi.api.testdummy.helper.AbsensiHelper
import org.json.JSONObject

class AbsenViewModel: ViewModel() {

    private var _listAbsensi = MutableLiveData<ArrayList<DataAbsensi>>()
    val listAbsensi : LiveData<ArrayList<DataAbsensi>> = _listAbsensi

    fun getAbsensi() {

        ApiConfigDummy.getListAbsensi(object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                try {

                    val responseObject = JSONObject(result)
                    val arrayObject = responseObject.getJSONArray("data")
                    _listAbsensi.postValue(AbsensiHelper.listCarouselResponse(arrayObject))
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }

        })
    }
}