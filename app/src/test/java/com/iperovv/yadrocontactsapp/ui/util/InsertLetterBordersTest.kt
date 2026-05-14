package com.iperovv.yadrocontactsapp.ui.util

import com.iperovv.yadrocontactsapp.domain.model.ContactData
import com.iperovv.yadrocontactsapp.domain.model.ContactListItem
import org.junit.Assert.assertEquals
import org.junit.Test

class InsertLetterBordersTest {
    @Test
    fun emptyList_returnsEmptyList() {
        assertEquals(emptyList<ContactListItem>(), insertLetterBorders(emptyList()))
    }

    @Test
    fun singleContact_BorderAndContactExpected() {
        val contact = contact("Anna")
        val expected =
            listOf(
                ContactListItem.ContactLetterBorder('A'),
                ContactListItem.Contact(contact),
            )
        assertEquals(expected, insertLetterBorders(listOf(contact)))
    }

    @Test
    fun contactsWithDifferentCases_inSameGroup() {
        val a1 = contact("anna", id = "1")
        val a2 = contact("Alice", id = "2")
        val expected =
            listOf(
                ContactListItem.ContactLetterBorder('A'),
                ContactListItem.Contact(a1),
                ContactListItem.Contact(a2),
            )
        assertEquals(expected, insertLetterBorders(listOf(a1, a2)))
    }

    @Test
    fun multipleLetters_sortedInDifferentGroupsInEncounterOrder() {
        val bob = contact("Bob", id = "1")
        val anna = contact("Anna", id = "2")
        val expected =
            listOf(
                ContactListItem.ContactLetterBorder('A'),
                ContactListItem.Contact(anna),
                ContactListItem.ContactLetterBorder('B'),
                ContactListItem.Contact(bob),
            )
        assertEquals(expected, insertLetterBorders(listOf(bob, anna)))
    }

    @Test
    fun sameLetterInMultipleContacts_sortedByInputOrder() {
        val c1 = contact("Charlie", id = "1")
        val c2 = contact("Chris", id = "2")
        val expected =
            listOf(
                ContactListItem.ContactLetterBorder('C'),
                ContactListItem.Contact(c1),
                ContactListItem.Contact(c2),
            )
        assertEquals(expected, insertLetterBorders(listOf(c1, c2)))
    }

    private fun contact(
        name: String,
        id: String = name,
    ): ContactData =
        ContactData(
            id = id,
            image = "",
            phoneNumber = "",
            name = name,
        )
}
