package com.iperovv.yadrocontactsapp.data.telephony

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.iperovv.yadrocontactsapp.domain.telephony.CheckCallPermissionStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckCallPermissionStatusStatusImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : CheckCallPermissionStatus {
    override fun isGranted(): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CALL_PHONE,
        ) == PackageManager.PERMISSION_GRANTED
}
