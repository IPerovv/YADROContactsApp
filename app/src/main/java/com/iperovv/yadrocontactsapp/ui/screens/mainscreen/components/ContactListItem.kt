package com.iperovv.yadrocontactsapp.ui.screens.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iperovv.yadrocontactsapp.domain.model.ContactData
import com.iperovv.yadrocontactsapp.ui.common.PreviewData

@Composable
fun ContactListUIItem(
    contactData: ContactData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(all = 3.dp),
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().height(3.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ContactAvatar(
                imageUri = contactData.image,
                contentDescription = contactData.name.ifBlank { contactData.phoneNumber },
            )
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
            ) {
                Text(contactData.name, fontWeight = FontWeight.Medium)
                Text(contactData.phoneNumber, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(3.dp))
    }
}

@Composable
private fun ContactAvatar(
    imageUri: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val avatarModifier =
        modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Blue)

    if (imageUri.isBlank()) {
        Box(modifier = avatarModifier)
        return
    }

    val placeholder = ColorPainter(Color.Blue)
    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(imageUri)
                .crossfade(true)
                .build(),
        contentDescription = contentDescription,
        modifier = avatarModifier,
        contentScale = ContentScale.Crop,
        placeholder = placeholder,
        error = placeholder,
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewContactListUIItem() {
    ContactListUIItem(
        contactData = PreviewData.previewContactsList.first(),
    )
}
