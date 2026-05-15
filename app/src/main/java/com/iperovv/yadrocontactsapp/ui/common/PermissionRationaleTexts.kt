package com.iperovv.yadrocontactsapp.ui.common

import android.Manifest
import androidx.annotation.StringRes
import com.iperovv.yadrocontactsapp.R

object PermissionRationaleTexts {
    fun providerFor(permission: String): PermissionTextProvider =
        when (permission) {
            Manifest.permission.READ_CONTACTS -> ReadContactsPermissionTextProvider
            Manifest.permission.CALL_PHONE -> PhoneCallPermissionTextProvider
            else -> error("Unknown permission: $permission")
        }
}

private object ReadContactsPermissionTextProvider : PermissionTextProvider {
    @StringRes
    override fun descriptionRes(isPermanentlyDeclined: Boolean): Int =
        if (isPermanentlyDeclined) {
            R.string.permission_read_contacts_permanently_declined
        } else {
            R.string.permission_read_contacts_rationale
        }
}

private object PhoneCallPermissionTextProvider : PermissionTextProvider {
    @StringRes
    override fun descriptionRes(isPermanentlyDeclined: Boolean): Int =
        if (isPermanentlyDeclined) {
            R.string.permission_call_phone_permanently_declined
        } else {
            R.string.permission_call_phone_rationale
        }
}
