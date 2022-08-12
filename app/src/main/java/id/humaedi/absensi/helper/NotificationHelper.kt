package id.humaedi.absensi.helper

import id.humaedi.absensi.data.DataNotification
import org.json.JSONArray

class NotificationHelper {
    companion object {
        fun listCarouselResponse(items: JSONArray): ArrayList<DataNotification> {
            val listCarousel = ArrayList<DataNotification>()
            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)
                val dataBook = DataNotification()
                dataBook.en = item.getString("en")
                dataBook.author = item.getString("author")
                listCarousel.add(dataBook)
            }
            return listCarousel
        }
    }
}