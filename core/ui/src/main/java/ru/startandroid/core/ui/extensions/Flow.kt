package ru.startandroid.core.ui.extensions

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
fun <T> StateFlow<T>.collectValue(): T = collectAsStateWithLifecycle().value

context(ViewModel)
fun <T> Flow<List<T>>.stateInVm(): StateFlow<List<T>> = stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList<T>())