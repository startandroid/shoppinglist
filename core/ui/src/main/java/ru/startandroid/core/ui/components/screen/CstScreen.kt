package ru.startandroid.core.ui.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.startandroid.core.ui.components.overlay.DismissibleOverlay

@Composable
fun CstScreen(
    topBar: @Composable () -> Unit,
    fab: @Composable () -> Unit = {},
    showFabOverlay: Boolean = false,
    onDismissFabOverlay: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            topBar()
            Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                content()
            }
        }
        DismissibleOverlay(
            visible = showFabOverlay,
            onDismiss = onDismissFabOverlay
        )
        Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            fab()
        }
    }
}