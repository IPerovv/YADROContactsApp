package com.iperovv.yadrocontactsapp.data.contacts

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.iperovv.yadrocontactsapp.domain.contacts.CheckContactsPermissionStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckContactsPermissionStatusImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : CheckContactsPermissionStatus {
    override fun isGranted(): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_CONTACTS,
        ) == PackageManager.PERMISSION_GRANTED
}
