package com.iperovv.yadrocontactsapp.data.contacts

import com.iperovv.yadrocontactsapp.domain.contacts.ContactsRepository
import com.iperovv.yadrocontactsapp.domain.model.ContactData
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val deviceContactsDataSource: DeviceContactsDataSource,
) : ContactsRepository {
    override suspend fun getContacts(): List<ContactData> = deviceContactsDataSource.loadContacts()
}
