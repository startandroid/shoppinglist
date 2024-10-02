package ru.startandroid.core.ui.components.dialog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CstConfirmationDialogStateHolder @Inject constructor() {
    private val _visible = MutableStateFlow(false)
    internal val visible = _visible.asStateFlow()

    fun show() {
        _visible.value = true
    }

    internal fun dismiss() {
        _visible.value = false
    }
}

@Composable
fun CstConfirmationDialog(
    holder: CstConfirmationDialogStateHolder,
    message: String,
    positiveText: String,
    positiveAction: () -> Unit
) {
    if (!holder.visible.collectAsState().value) return
    CstConfirmationDialog(
        onDismissRequest = holder::dismiss,
        message = message,
        positiveText = positiveText,
        positiveAction = {
            holder.dismiss()
            positiveAction()
        }
    )
}

@Composable
fun CstConfirmationDialog(
    onDismissRequest: () -> Unit,
    message: String,
    positiveText: String,
    positiveAction: () -> Unit
) {
    CstAlertDialog(
        onDismissRequest = onDismissRequest,
        negativeText = "Cancel",
        negativeAction = onDismissRequest,
        positiveText = positiveText,
        positiveAction = positiveAction
    ) {
        Text(text = message)
    }
}