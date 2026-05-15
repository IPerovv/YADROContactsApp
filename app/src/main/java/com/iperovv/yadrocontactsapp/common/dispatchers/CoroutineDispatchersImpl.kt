package com.iperovv.yadrocontactsapp.common.dispatchers

import com.iperovv.yadrocontactsapp.di.qualifiers.DefaultDispatcher
import com.iperovv.yadrocontactsapp.di.qualifiers.IoDispatcher
import com.iperovv.yadrocontactsapp.di.qualifiers.MainDispatcher
import com.iperovv.yadrocontactsapp.di.qualifiers.UnconfinedDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CoroutineDispatchersImpl @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @UnconfinedDispatcher private val unconfinedDispatcher: CoroutineDispatcher,
) : CoroutineDispatchers {
    override fun main(): CoroutineDispatcher = mainDispatcher

    override fun io(): CoroutineDispatcher = ioDispatcher

    override fun default(): CoroutineDispatcher = defaultDispatcher

    override fun unconfined(): CoroutineDispatcher = unconfinedDispatcher
}
