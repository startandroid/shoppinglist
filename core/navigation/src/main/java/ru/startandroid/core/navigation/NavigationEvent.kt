package ru.startandroid.core.navigation

sealed class NavigationEvent
class NavigateTo(val route: Any) : NavigationEvent()