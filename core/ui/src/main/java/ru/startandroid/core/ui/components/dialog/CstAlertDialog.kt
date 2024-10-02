package ru.startandroid.core.ui.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CstAlertDialog(
    onDismissRequest: () -> Unit,
    negativeText: String? = null,
    negativeAction: () -> Unit = {},
    positiveText: String? = null,
    positiveAction: () -> Unit = {},
    positiveActionEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 32.dp)) {
                content()
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {

                    negativeText?.let {
                        Button(it) {
                            onDismissRequest()
                            negativeAction()
                        }
                    }
                    positiveText?.let {
                        Button(it, positiveActionEnabled) {
                            onDismissRequest()
                            positiveAction()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Button(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick, enabled = enabled) {
        Text(text = text)
    }
}