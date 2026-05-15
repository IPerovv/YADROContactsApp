package com.iperovv.yadrocontactsapp.ui.screens.mainscreen

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.iperovv.yadrocontactsapp.common.permissions.AppPermissions
import com.iperovv.yadrocontactsapp.domain.model.ContactData
import com.iperovv.yadrocontactsapp.domain.model.ContactListItem
import com.iperovv.yadrocontactsapp.ui.common.PermissionDialog
import com.iperovv.yadrocontactsapp.ui.common.PermissionRationaleTexts
import com.iperovv.yadrocontactsapp.ui.common.PreviewData
import com.iperovv.yadrocontactsapp.ui.screens.mainscreen.components.ContactListUIItem
import com.iperovv.yadrocontactsapp.ui.screens.mainscreen.components.LetterBorder
import com.iperovv.yadrocontactsapp.ui.util.insertLetterBorders
import com.iperovv.yadrocontactsapp.util.openAppSettings

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val resources = LocalContext.current.resources
    val activity = LocalActivity.current as ComponentActivity

    val multiplePermissionsLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
        ) { results ->
            viewModel.onPermissionsResult(results)
        }

    val singlePermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
        ) { isGranted ->
            viewModel.onSinglePermissionResult(isGranted)
        }

    LaunchedEffect(Unit) {
        multiplePermissionsLauncher.launch(AppPermissions.onLaunch)
    }

    uiState.permissionDialogQueue.firstOrNull()?.let { permission ->
        val isPermanentlyDeclined =
            !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission) &&
                ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED

        PermissionDialog(
            permissionTextProvider = PermissionRationaleTexts.providerFor(permission, resources),
            isPermanentlyDeclined = isPermanentlyDeclined,
            onDismiss = viewModel::dismissDialog,
            onOkClick = {
                viewModel.dismissDialog()
                singlePermissionLauncher.launch(permission)
            },
            onGoToAppSettingsClick = {
                viewModel.dismissDialog()
                activity.openAppSettings()
            },
        )
    }

    Content(
        modifier = modifier,
        contacts = uiState.contacts,
        onContactClick = { contact -> viewModel.onCallRequested(contact.phoneNumber) },
    )
}

@Composable
fun Content(
    contacts: List<ContactData>,
    modifier: Modifier = Modifier,
    onContactClick: (ContactData) -> Unit = {},
) {
    val items = remember(contacts) { insertLetterBorders(contacts) }

    LazyColumn(modifier = modifier) {
        items(
            items = items,
            key = { item ->
                when (item) {
                    is ContactListItem.ContactLetterBorder -> item.letter
                    is ContactListItem.Contact -> item.contact.id
                }
            },
        ) { item ->
            when (item) {
                is ContactListItem.ContactLetterBorder -> {
                    LetterBorder(letter = item.letter.toString())
                }

                is ContactListItem.Contact -> {
                    ContactListUIItem(
                        modifier =
                            Modifier
                                .padding(start = 20.dp, end = 15.dp)
                                .clickable { onContactClick(item.contact) },
                        contactData = item.contact,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContent() {
    Content(
        contacts = PreviewData.previewContactsList,
    )
}
