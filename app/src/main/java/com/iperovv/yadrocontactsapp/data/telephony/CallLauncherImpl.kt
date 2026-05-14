package com.iperovv.yadrocontactsapp.data.telephony

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.iperovv.yadrocontactsapp.domain.telephony.CallLauncher
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.net.toUri

class CallLauncherImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : CallLauncher {
    override fun makeCall(phoneNumber: String): Result<Unit> =
        runCatching {
            val trimmed = phoneNumber.trim()
            require(trimmed.isNotEmpty()) { "phoneNumber must not be blank" }
            val uri = "tel:${Uri.encode(trimmed)}".toUri()
            val intent =
                Intent(Intent.ACTION_CALL, uri).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context.startActivity(intent)
        }
}
