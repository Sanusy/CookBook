package com.gmail.ivan.morozyk.cookbook.ui.receiptlist

import com.gmail.ivan.morozyk.cookbook.redux.Command

sealed class ReceiptListProps {
    object EmptyList : ReceiptListProps()
    object Loading : ReceiptListProps()
    data class ReceiptList(val receiptList: List<Receipt>) : ReceiptListProps() {
        data class Receipt(
            val id: Int,
            val title: String,
            val image: String,
            val onReceiptClick: Command.With<String>
        )
    }
}