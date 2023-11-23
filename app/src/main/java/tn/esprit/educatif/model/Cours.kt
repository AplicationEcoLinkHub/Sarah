package tn.esprit.educatif.model

import android.os.Parcel
import android.os.Parcelable

data class Cours(
    val _id: String,
    val titleImage: String,
    val title: String,
    val header: String,
    val favori:Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(titleImage)
        parcel.writeString(title)
        parcel.writeString(header)
        parcel.writeByte(if (favori) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cours> {
        override fun createFromParcel(parcel: Parcel): Cours {
            return Cours(parcel)
        }

        override fun newArray(size: Int): Array<Cours?> {
            return arrayOfNulls(size)
        }
    }
}
