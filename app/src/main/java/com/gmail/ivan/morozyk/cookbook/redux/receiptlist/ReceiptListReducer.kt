package com.gmail.ivan.morozyk.cookbook.redux.receiptlist

import com.gmail.ivan.morozyk.cookbook.redux.base.Action
import com.gmail.ivan.morozyk.cookbook.redux.base.Reducer

class ReceiptListReducer : Reducer<ReceiptListState> {
    override fun reduce(state: ReceiptListState, action: Action): ReceiptListState = when (action) {
        is LoadReceiptList -> {
            state.copy(isLoading = true)
        }
        is ReceiptListLoaded -> {
            val receipts = action.receiptList.map { receiptDto ->
                ReceiptListState.Receipt(
                    receiptDto.id,
                    receiptDto.title,
                    receiptDto.image
                )
            }
            state.copy(isLoading = false, receiptList = receipts)
        }
        else -> state
    }
}