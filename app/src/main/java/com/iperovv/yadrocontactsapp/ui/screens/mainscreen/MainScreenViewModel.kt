package com.iperovv.yadrocontactsapp.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iperovv.yadrocontactsapp.common.permissions.AppPermissions
import com.iperovv.yadrocontactsapp.domain.telephony.CallLauncher
import com.iperovv.yadrocontactsapp.domain.telephony.CheckCallPermissionStatus
import com.iperovv.yadrocontactsapp.ui.common.PreviewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val callLauncher: CallLauncher,
    private val callPermissionStatus: CheckCallPermissionStatus,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    private var pendingCallPhoneNumber: String? = null

    init {
        loadContacts()
    }

    fun loadContacts() {
        viewModelScope.launch {
            _uiState.update { it.copy(contacts = PreviewData.previewContactsList) }
        }
    }

    fun onCallRequested(phoneNumber: String) {
        if (callPermissionStatus.isGranted()) {
            callLauncher.makeCall(phoneNumber)
        } else {
            pendingCallPhoneNumber = phoneNumber
            enqueuePermissionDialog(AppPermissions.CALL_PHONE)
        }
    }

    fun onPermissionsResult(results: Map<String, Boolean>) {
        results.forEach { (permission, isGranted) ->
            onPermissionResult(permission, isGranted)
        }
    }

    fun onSinglePermissionResult(isGranted: Boolean) {
        val permission = _uiState.value.permissionDialogQueue.firstOrNull() ?: return
        onPermissionResult(permission, isGranted)
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean,
    ) {
        if (isGranted) {
            if (permission == AppPermissions.CALL_PHONE) {
                pendingCallPhoneNumber?.let { callLauncher.makeCall(it) }
                pendingCallPhoneNumber = null
            }
        } else {
            enqueuePermissionDialog(permission)
        }
    }

    fun dismissDialog() {
        _uiState.update { state ->
            if (state.permissionDialogQueue.isEmpty()) {
                state
            } else {
                state.copy(permissionDialogQueue = state.permissionDialogQueue.drop(1))
            }
        }
    }

    private fun enqueuePermissionDialog(permission: String) {
        _uiState.update { state ->
            if (permission in state.permissionDialogQueue) {
                state
            } else {
                state.copy(permissionDialogQueue = state.permissionDialogQueue + permission)
            }
        }
    }
}
