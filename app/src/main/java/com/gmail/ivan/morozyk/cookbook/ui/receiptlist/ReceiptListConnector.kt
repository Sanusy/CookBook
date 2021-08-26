package com.gmail.ivan.morozyk.cookbook.ui.receiptlist

import com.gmail.ivan.morozyk.cookbook.redux.Command
import com.gmail.ivan.morozyk.cookbook.redux.Store
import com.gmail.ivan.morozyk.cookbook.redux.base.Connector

class ReceiptListConnector : Connector<ReceiptListProps> {

    override fun connect(): ReceiptListProps {

        val receiptListState = Store.state.receiptListState

        if (receiptListState.isLoading) return ReceiptListProps.Loading

        return when (receiptListState.receiptList.isNullOrEmpty()) {
            true -> {
                ReceiptListProps.EmptyList
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

                ReceiptListProps.ReceiptList(receiptList = receiptList)
            }
        }
    }
}