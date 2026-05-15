package com.iperovv.yadrocontactsapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iperovv.yadrocontactsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
    ) {
        Surface(
            shape = AlertDialogDefaults.shape,
            color = AlertDialogDefaults.containerColor,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.permission_required_title),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 16.dp),
                )
                Text(
                    text =
                        stringResource(
                            permissionTextProvider.descriptionRes(
                                isPermanentlyDeclined = isPermanentlyDeclined,
                            ),
                        ),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(24.dp, 0.dp, 24.dp, 16.dp),
                )
                HorizontalDivider(
                    Modifier,
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                )
                Text(
                    text =
                        if (isPermanentlyDeclined) {
                            stringResource(R.string.grant_permission)
                        } else {
                            stringResource(R.string.ok)
                        },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (isPermanentlyDeclined) {
                                    onGoToAppSettingsClick()
                                } else {
                                    onOkClick()
                                }
                            }.padding(16.dp),
                )
            }
        }
    }
}
