package com.mobifyall.githubapi.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.commons.TAG_SEARCH_INPUT
import com.mobifyall.githubapi.ui.theme.Typography
import com.mobifyall.githubapi.viewstates.SearchBarUIState

@Composable
fun SearchInputComponent(uiState: SearchBarUIState, updateInput: (String) -> Unit, doneClicked: () -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = uiState.inputState,
        onValueChange = updateInput,
        textStyle = Typography.body1.copy(Color.White),
        modifier = Modifier.focusRequester(focusRequester)
            .fillMaxWidth()
            .height(height = 40.dp)
            .testTag(TAG_SEARCH_INPUT),
        maxLines = 1,
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            doneClicked.invoke()
        }),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        decorationBox = { innerTextField ->
            Column(
                Modifier
                    .padding(8.dp),
            ) {
                innerTextField() //
                //todo add clear button
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}