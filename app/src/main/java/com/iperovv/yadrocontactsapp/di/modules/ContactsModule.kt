package com.iperovv.yadrocontactsapp.di.modules

import com.iperovv.yadrocontactsapp.data.contacts.CheckContactsPermissionStatusImpl
import com.iperovv.yadrocontactsapp.data.contacts.ContactsRepositoryImpl
import com.iperovv.yadrocontactsapp.domain.contacts.CheckContactsPermissionStatus
import com.iperovv.yadrocontactsapp.domain.contacts.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ContactsModule {
    @Binds
    abstract fun bindContactsRepository(impl: ContactsRepositoryImpl): ContactsRepository

    @Binds
    abstract fun bindCheckContactsPermissionStatus(impl: CheckContactsPermissionStatusImpl): CheckContactsPermissionStatus
}
