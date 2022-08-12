package id.humaedi.absensi.data

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class DataNotification(
    var en: String? = null,
    var author: String? = null,
) : Parcelable
