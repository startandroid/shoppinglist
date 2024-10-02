package ru.startandroid.core.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CstTopBar(
    text: String,
    iconsContent: @Composable RowScope.() -> Unit = {},
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .height(64.dp)
                .background(color = Color.LightGray)
                .padding(16.dp)
        ) {
            Text(text, fontSize = 24.sp, modifier = Modifier.weight(1f))
            Row { iconsContent() }
        }
}

@Composable
fun CstTopBarIcon(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun CstTopBarIcon(
    iconResId: Int,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null
        )
    }
}