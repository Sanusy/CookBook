package com.gmail.ivan.morozyk.cookbook.ui.receiptlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.ivan.morozyk.cookbook.ui.widgets.ProgressBar
import com.gmail.ivan.morozyk.cookbook.ui.widgets.SearchBar

@Composable
fun ReceiptListScreen(props: ReceiptListProps) {
    Scaffold(topBar = {
        SearchBar(
            query = props.toolBarProps.query,
            onQueryChanged = props.toolBarProps.onQueryChanged::invoke,
            onClearSearch = props.toolBarProps.onClearSearch::invoke,
            onSearchPerformed = props.toolBarProps.onSearchPerformed::invoke
        )
    }) {
        when (props) {
            is ReceiptListProps.EmptyList -> {
            }
            is ReceiptListProps.Loading -> ProgressBar()
            is ReceiptListProps.ReceiptList -> ReceiptList(props = props)
        }
    }
}


@Composable
fun ReceiptList(props: ReceiptListProps.ReceiptList) {
    LazyColumn {
        items(props.receiptList) {
            ReceiptItem(receipt = it)
        }
    }
}

@Composable
fun ReceiptItem(receipt: ReceiptListProps.ReceiptList.Receipt) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Surface(modifier = Modifier.size(50.dp), shape = CircleShape) { }
        Text(text = receipt.title)
    }
}