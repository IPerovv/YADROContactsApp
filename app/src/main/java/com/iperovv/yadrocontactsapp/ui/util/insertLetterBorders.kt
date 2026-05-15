package com.iperovv.yadrocontactsapp.ui.util

import com.iperovv.yadrocontactsapp.domain.model.ContactData
import com.iperovv.yadrocontactsapp.domain.model.ContactListItem

fun insertLetterBorders(contacts: List<ContactData>): List<ContactListItem> {
    val grouped =
        contacts.groupBy { contact ->
            contact.name.firstOrNull()?.uppercaseChar() ?: '#'
        }
    val result = mutableListOf<ContactListItem>()

    grouped.toSortedMap().forEach { (letter, contactsInGroup) ->
        result.add(ContactListItem.ContactLetterBorder(letter))
        result.addAll(contactsInGroup.map { ContactListItem.Contact(it) })
    }
    return result
}
