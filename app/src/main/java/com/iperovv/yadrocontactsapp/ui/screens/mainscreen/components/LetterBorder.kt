package com.iperovv.yadrocontactsapp.ui.screens.mainscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iperovv.yadrocontactsapp.ui.common.PreviewData

@Composable
fun LetterBorder(
    letter: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = letter,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLetterBorder() {
    LetterBorder(
        letter =
            PreviewData.previewContactsList
                .first()
                .name
                .first()
                .toString(),
    )
}
