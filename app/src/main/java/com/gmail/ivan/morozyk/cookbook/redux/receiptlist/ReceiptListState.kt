package com.gmail.ivan.morozyk.cookbook.redux.receiptlist

data class ReceiptListState(
    val offset: Int = 0,
    val isLoading: Boolean = false,
    val receiptList: List<Receipt>? = null,
) {
    data class Receipt(val id: Int, val title: String, val image: String)
}