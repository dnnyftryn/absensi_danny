package id.humaedi.absensi.api.testdummy.data

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class DataAbsensi(
    var jam: String? = null,
    var keterangan: String? = null,
) : Parcelable
