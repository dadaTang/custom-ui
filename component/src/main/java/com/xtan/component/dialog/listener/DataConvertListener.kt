package com.xtan.component.dialog.listener

import android.os.Parcel
import android.os.Parcelable
import com.xtan.component.dialog.CustomDialog

abstract class DataConvertListener : Parcelable {

    abstract fun convertView(dialogBinding: Any, dialog: CustomDialog)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    constructor() {}

    protected constructor(source: Parcel) {}

    companion object {
        val CREATOR: Parcelable.Creator<DataConvertListener> = object : Parcelable.Creator<DataConvertListener> {
            override fun createFromParcel(source: Parcel): DataConvertListener {
                return object : DataConvertListener(source) {
                    override fun convertView(dialogBinding: Any, dialog: CustomDialog) {

                    }
                }
            }

            override fun newArray(size: Int): Array<DataConvertListener?> {
                return arrayOfNulls(size)
            }
        }
    }
}
