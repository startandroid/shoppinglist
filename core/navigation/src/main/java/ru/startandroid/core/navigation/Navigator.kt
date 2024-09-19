package ru.startandroid.core.navigation

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@ActivityRetainedScoped
class Navigator @Inject constructor() {

    private val _events = MutableSharedFlow<NavigationEvent>(
        onBufferOverflow = BufferOverflow.SUSPEND,
        extraBufferCapacity = 1
    )

    val events = _events.asSharedFlow()

    fun navigateTo(route: String) {
        _events.tryEmit(NavigateTo(route))
    }

}