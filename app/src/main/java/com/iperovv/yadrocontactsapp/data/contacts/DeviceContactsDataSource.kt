package com.iperovv.yadrocontactsapp.data.contacts

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import com.iperovv.yadrocontactsapp.common.dispatchers.CoroutineDispatchers
import com.iperovv.yadrocontactsapp.domain.model.ContactData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeviceContactsDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatchers: CoroutineDispatchers,
) {
    suspend fun loadContacts(): List<ContactData> =
        withContext(dispatchers.io()) {
            runCatching { readAllContacts() }
                .getOrDefault(emptyList())
        }

    private fun readAllContacts(): List<ContactData> {
        val contacts = LinkedHashMap<Long, ContactData>()

        context.contentResolver
            .query(
                Phone.CONTENT_URI,
                arrayOf(
                    Phone.CONTACT_ID,
                    Phone.DISPLAY_NAME,
                    Phone.NUMBER,
                    Phone.PHOTO_URI,
                ),
                "${Phone.IS_PRIMARY} = ?",
                arrayOf("1"),
                "${Phone.DISPLAY_NAME} ASC",
            )?.use { cursor ->
                val contactIdIndex = cursor.getColumnIndexOrThrow(Phone.CONTACT_ID)
                val displayNameIndex = cursor.getColumnIndexOrThrow(Phone.DISPLAY_NAME)
                val numberIndex = cursor.getColumnIndexOrThrow(Phone.NUMBER)
                val photoUriIndex = cursor.getColumnIndexOrThrow(Phone.PHOTO_URI)

                while (cursor.moveToNext()) {
                    val contactId = cursor.getLong(contactIdIndex)
                    if (contactId in contacts) continue

                    val number = cursor.getString(numberIndex)?.trim().orEmpty()
                    if (number.isEmpty()) continue

                    contacts[contactId] =
                        ContactData(
                            id = contactId.toString(),
                            image = cursor.getString(photoUriIndex).orEmpty(),
                            phoneNumber = number,
                            name = cursor.getString(displayNameIndex).orEmpty().trim(),
                        )
                }
            }

        return contacts.values.toList()
    }
}
