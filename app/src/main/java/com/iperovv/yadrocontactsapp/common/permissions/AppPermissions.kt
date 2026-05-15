package com.iperovv.yadrocontactsapp.common.permissions

import android.Manifest

object AppPermissions {
    const val READ_CONTACTS: String = Manifest.permission.READ_CONTACTS
    const val CALL_PHONE: String = Manifest.permission.CALL_PHONE

    val onLaunch: Array<String> =
        arrayOf(
            READ_CONTACTS,
            CALL_PHONE,
        )
}
