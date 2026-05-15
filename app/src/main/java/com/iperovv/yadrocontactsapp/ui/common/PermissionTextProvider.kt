package com.iperovv.yadrocontactsapp.ui.common

import androidx.annotation.StringRes

interface PermissionTextProvider {
    @StringRes
    fun descriptionRes(isPermanentlyDeclined: Boolean): Int
}
