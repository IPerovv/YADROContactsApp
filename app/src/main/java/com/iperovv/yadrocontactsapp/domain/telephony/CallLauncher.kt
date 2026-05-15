package com.iperovv.yadrocontactsapp.domain.telephony

interface CallLauncher {
    fun makeCall(phoneNumber: String): Result<Unit>
}
