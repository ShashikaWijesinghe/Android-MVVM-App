package com.test.androidmvvmapp.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(
    hint: String,
    onValueChanged: (String) -> Unit,
    onClearText: () -> Unit
) {

    val searchKey = rememberSaveable{mutableStateOf("")}

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(hint) },
            value = searchKey.value,
            onValueChange = {
                searchKey.value = it
                onValueChanged(it)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                if (searchKey.value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchKey.value = ""
                            onClearText()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear Icon"
                        )
                    }

                }
            }
        )
    }
}