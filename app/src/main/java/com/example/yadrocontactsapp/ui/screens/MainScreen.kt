package com.example.yadrocontactsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.yadrocontactsapp.domain.ContactData
import com.example.yadrocontactsapp.domain.ContactListItem
import com.example.yadrocontactsapp.ui.common.PreviewData
import com.example.yadrocontactsapp.ui.components.ContactListUIItem
import com.example.yadrocontactsapp.ui.components.LetterBorder
import com.example.yadrocontactsapp.ui.util.insertLetterBorders

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    Content(
        modifier = modifier,
        contacts = PreviewData.previewContactsList,
    )
}

@Composable
fun Content(
    contacts: List<ContactData>,
    modifier: Modifier = Modifier,
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
                                .clickable(onClick = {}),
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
