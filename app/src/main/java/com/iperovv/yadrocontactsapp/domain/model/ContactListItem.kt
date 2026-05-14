package com.iperovv.yadrocontactsapp.domain.model

sealed class ContactListItem {
    data class ContactLetterBorder(
        val letter: Char,
    ) : ContactListItem()

    data class Contact(
        val contact: ContactData,
    ) : ContactListItem()
}
