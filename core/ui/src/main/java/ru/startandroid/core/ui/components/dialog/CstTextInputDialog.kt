package ru.startandroid.core.ui.components.dialog

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CstTextInputDialogStateHolder @Inject constructor()  {
    private val _inputValue = MutableStateFlow(TextFieldValue(""))
    internal val inputValue = _inputValue.asStateFlow()

    private val _visible = MutableStateFlow(false)
    internal val visible = _visible.asStateFlow()

    private var positiveEnabledMapFn: (String?) -> Boolean = { !it.isNullOrBlank() }
    internal val positiveEnabled = inputValue.map { positiveEnabledMapFn(it.text) }

    internal fun onValueChange(textFieldValue: TextFieldValue) {
        _inputValue.value = textFieldValue
    }

    internal fun dismiss() {
        _visible.value = false
        _inputValue.value = TextFieldValue("")
    }

    fun show(initialValue: String = "", positiveEnabledMapFn: ((String?) -> Boolean)? = null) {
        positiveEnabledMapFn?.let { this.positiveEnabledMapFn = it }
        _inputValue.value = TextFieldValue(initialValue, TextRange(initialValue.length))
        _visible.value = true
    }
}

@Composable
fun CstTextInputDialog(
    holder: CstTextInputDialogStateHolder,
    positiveText: String,
    positiveAction: (String) -> Unit,
) {
    if (!holder.visible.collectAsState().value) return
    val inputValue = holder.inputValue.collectAsState().value
    CstTextInputDialog(
        onDismissRequest = holder::dismiss,
        positiveText = positiveText,
        positiveAction = {
            holder.dismiss()
            positiveAction(inputValue.text)
        },
        positiveActionEnabled = holder.positiveEnabled.collectAsState(false).value,
        value = inputValue,
        onValueChange = holder::onValueChange,
    )
}


@Composable
fun CstTextInputDialog(
    onDismissRequest: () -> Unit,
    positiveText: String,
    positiveAction: () -> Unit,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    positiveActionEnabled: Boolean = true
) {
    val focusRequester = remember { FocusRequester() }

    CstAlertDialog(
        onDismissRequest = onDismissRequest,
        negativeText = "Cancel",
        negativeAction = onDismissRequest,
        positiveText = positiveText,
        positiveAction = positiveAction,
        positiveActionEnabled = positiveActionEnabled
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text("Enter text") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.focusRequester(focusRequester),
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}