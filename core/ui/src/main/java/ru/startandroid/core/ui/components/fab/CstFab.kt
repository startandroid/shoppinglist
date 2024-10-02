package ru.startandroid.core.ui.components.fab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CstMultipleFab(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    mainContent: @Composable () -> Unit,
    actions: List<@Composable () -> Unit>
) {
    if (expanded) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            for (action in actions) {
                Spacer(modifier = Modifier.height(12.dp))
                action()
            }
        }
    } else {
        CstFab(
            onClick = { onExpandedChange(true) },
            content = mainContent
        )
    }
}

@Composable
fun CstSmallFab(
    onClick: () -> Unit,
    label: String? = null,
    content: @Composable () -> Unit
) {
    CstFab(
        onClick = onClick,
        size = 48.dp,
        label = label,
        content = content
    )
}

@Composable
fun CstFab(
    onClick: () -> Unit,
    label: String? = null,
    size: Dp = 64.dp,
    content: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        label?.let {
            Text(text = it)
        }
        //Spacer(modifier = Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.width(80.dp)
        ) {
            FloatingActionButton(
                onClick = onClick,
                modifier = Modifier.size(size),
                content = content
            )
        }
    }

}