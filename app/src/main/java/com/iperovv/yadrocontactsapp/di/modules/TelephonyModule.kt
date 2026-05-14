package com.iperovv.yadrocontactsapp.di.modules

import com.iperovv.yadrocontactsapp.data.telephony.CallLauncherImpl
import com.iperovv.yadrocontactsapp.data.telephony.CheckCallPermissionStatusStatusImpl
import com.iperovv.yadrocontactsapp.domain.telephony.CallLauncher
import com.iperovv.yadrocontactsapp.domain.telephony.CheckCallPermissionStatus
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TelephonyModule {
    @Binds
    abstract fun bindCallLauncher(impl: CallLauncherImpl): CallLauncher

    @Binds
    abstract fun bindCheckCallPermissionStatus(impl: CheckCallPermissionStatusStatusImpl): CheckCallPermissionStatus
}
