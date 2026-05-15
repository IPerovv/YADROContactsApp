package com.iperovv.yadrocontactsapp.ui.common

import android.content.res.Resources
import androidx.annotation.StringRes
import com.iperovv.yadrocontactsapp.R
import com.iperovv.yadrocontactsapp.common.permissions.AppPermissions

object PermissionRationaleTexts {
    fun providerFor(
        permission: String,
        resources: Resources,
    ): PermissionTextProvider =
        when (permission) {
            AppPermissions.READ_CONTACTS -> ReadContactsPermissionTextProvider
            AppPermissions.CALL_PHONE -> PhoneCallPermissionTextProvider
            else -> error(resources.getString(R.string.unknown_permission, permission))
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
