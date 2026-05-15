package com.iperovv.yadrocontactsapp.domain.contacts

interface CheckContactsPermissionStatus {
    fun isGranted(): Boolean
}
