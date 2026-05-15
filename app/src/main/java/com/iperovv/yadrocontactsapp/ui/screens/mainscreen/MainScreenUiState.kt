package com.iperovv.yadrocontactsapp.ui.screens.mainscreen

import com.iperovv.yadrocontactsapp.domain.model.ContactData

data class MainScreenUiState(
    val contacts: List<ContactData> = emptyList(),
    val permissionDialogQueue: List<String> = emptyList(),
)
