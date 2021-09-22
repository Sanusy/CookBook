package com.gmail.ivan.morozyk.cookbook.ui.receiptlist

import com.gmail.ivan.morozyk.cookbook.redux.Command
import com.gmail.ivan.morozyk.cookbook.redux.Store
import com.gmail.ivan.morozyk.cookbook.redux.base.Connector
import com.gmail.ivan.morozyk.cookbook.redux.receiptlist.LoadReceiptList
import com.gmail.ivan.morozyk.cookbook.redux.receiptlist.OnQueryChanged

class ReceiptListConnector : Connector<ReceiptListProps> {

    override fun connect(): ReceiptListProps {

        val receiptListState = Store.state.receiptListState

        val toolBarProps = ReceiptListProps.ToolBarProps(
            query = receiptListState.query,
            onQueryChanged = Command.With { query -> Store.dispatch(OnQueryChanged(query)) },
            onClearSearch = Command { Store.dispatch(OnQueryChanged("")) },
            onSearchPerformed = Command { Store.dispatch(LoadReceiptList) }
        )

        if (receiptListState.isLoading) return ReceiptListProps.Loading(toolBarProps = toolBarProps)

        return when (receiptListState.receiptList.isNullOrEmpty()) {
            true -> {
                ReceiptListProps.EmptyList(toolBarProps = toolBarProps)
            }
            false -> {
                val receiptList = receiptListState.receiptList.map { receipt ->
                    ReceiptListProps.ReceiptList.Receipt(
                        receipt.id,
                        receipt.title,
                        receipt.image,
                        Command.With {

                        }
                    )
                }

                ReceiptListProps.ReceiptList(receiptList = receiptList, toolBarProps = toolBarProps)
            }
        }
    }
}