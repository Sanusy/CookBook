package com.gmail.ivan.morozyk.cookbook.ui.receiptlist

import com.gmail.ivan.morozyk.cookbook.redux.Command

sealed class ReceiptListProps {

    abstract val toolBarProps: ToolBarProps

    data class EmptyList(
        override val toolBarProps: ToolBarProps,
    ) : ReceiptListProps()

    data class Loading(
        override val toolBarProps: ToolBarProps,
    ) : ReceiptListProps()

    data class ReceiptList(
        override val toolBarProps: ToolBarProps,
        val receiptList: List<Receipt>,
    ) :
        ReceiptListProps() {

        data class Receipt(
            val id: Int,
            val title: String,
            val image: String,
            val onReceiptClick: Command.With<Int>,
        )
    }

    data class ToolBarProps(
        val query: String,
        val onQueryChanged: Command.With<String>,
        val onClearSearch: Command,
        val onSearchPerformed: Command,
    )
}