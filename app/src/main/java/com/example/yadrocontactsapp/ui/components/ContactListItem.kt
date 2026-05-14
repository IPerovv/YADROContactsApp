package com.example.yadrocontactsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yadrocontactsapp.domain.ContactData
import com.example.yadrocontactsapp.ui.common.PreviewData

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
            // TODO Заменю на Image когда пойму формат получаемой картинки
            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(840.dp))
                        .background(Color.Blue),
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
@Preview(showBackground = true)
private fun PreviewContactListUIItem() {
    ContactListUIItem(
        contactData = PreviewData.previewContactsList.first(),
    )
}
