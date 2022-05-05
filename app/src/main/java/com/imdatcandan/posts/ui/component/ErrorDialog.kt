package com.imdatcandan.posts.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.imdatcandan.posts.R

@Composable
fun ErrorDialog(message: String, action: () -> Unit) {
    val openDialog = rememberSaveable { mutableStateOf(true) }

    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_error_title),
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_error_message, message),
                )
            },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                    action.invoke()
                }
                ) {
                    Text(text = stringResource(id = R.string.dialog_error_button))
                }
            }
        )
    }
}