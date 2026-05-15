package com.iperovv.yadrocontactsapp.di.modules

import com.iperovv.yadrocontactsapp.common.dispatchers.CoroutineDispatchers
import com.iperovv.yadrocontactsapp.common.dispatchers.CoroutineDispatchersImpl
import com.iperovv.yadrocontactsapp.di.qualifiers.DefaultDispatcher
import com.iperovv.yadrocontactsapp.di.qualifiers.IoDispatcher
import com.iperovv.yadrocontactsapp.di.qualifiers.MainDispatcher
import com.iperovv.yadrocontactsapp.di.qualifiers.UnconfinedDispatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineDispatchersModule {
    @Binds
    @Singleton
    fun bindCoroutineDispatchers(impl: CoroutineDispatchersImpl): CoroutineDispatchers

    companion object {
        @IoDispatcher
        @Provides
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @MainDispatcher
        @Provides
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @DefaultDispatcher
        @Provides
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @UnconfinedDispatcher
        @Provides
        fun provideUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
    }
}
