package com.iperovv.yadrocontactsapp.domain.contacts

import com.iperovv.yadrocontactsapp.domain.model.ContactData

interface ContactsRepository {
    suspend fun getContacts(): List<ContactData>
}
