package id.humaedi.absensi.api.testdummy.helper

import id.humaedi.absensi.api.testdummy.data.DataAbsensi
import id.humaedi.absensi.data.DataNotification
import org.json.JSONArray

class AbsensiHelper {
    companion object {
        fun listCarouselResponse(items: JSONArray): ArrayList<DataAbsensi> {
            val listCarousel = ArrayList<DataAbsensi>()
            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)
                val dataAbsensi = DataAbsensi()
                dataAbsensi.jam = item.getString("jam")
                dataAbsensi.keterangan = item.getString("keterangan")
                listCarousel.add(dataAbsensi)
            }
            return listCarousel
        }
    }
}