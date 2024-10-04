package ru.startandroid.core.ui.components.overlay

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DismissibleOverlay(
    visible: Boolean,
    onDismiss: () -> Unit
) {
    BackHandler(enabled = visible) {
        onDismiss()
    }

    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.5f))
                .clickable(
                    onClick = onDismiss,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
        )
    }
}