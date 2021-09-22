package com.gmail.ivan.morozyk.cookbook.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.ivan.morozyk.cookbook.R
import com.gmail.ivan.morozyk.cookbook.ui.theme.CookBookTheme

@Composable
fun SearchBar(
    query: String,
    toolbarTitle: String? = null,
    onQueryChanged: (String) -> Unit,
    onClearSearch: () -> Unit,
    onSearchPerformed: () -> Unit
) {
    val searchActive = rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (searchActive.value) {
                Column(Modifier.padding(end = 16.dp)) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 4.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BasicTextField(
                            value = query,
                            onValueChange = onQueryChanged,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = { onSearchPerformed() }),
                            cursorBrush = SolidColor(Color.White),
                            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                        )
                        if (query.isNotEmpty()) {
                            IconButton(onClick = onClearSearch) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Color.Gray,
                        thickness = 1.dp,
                    )
                }
            } else {
                Text(if (toolbarTitle.isNullOrEmpty()) stringResource(id = R.string.app_name) else toolbarTitle)
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                onClearSearch()
                searchActive.value = false
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            if (!searchActive.value) {
                IconButton(onClick = {
                    searchActive.value = true
                }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        },
    )

}

@Preview
@Composable
fun SearchBarPreview() {
    CookBookTheme {
        SearchBar("", null, {}, {}, {})
    }
}