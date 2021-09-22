package com.gmail.ivan.morozyk.cookbook.redux.receiptlist

import com.gmail.ivan.morozyk.cookbook.data.service.ReceiptService
import com.gmail.ivan.morozyk.cookbook.redux.State
import com.gmail.ivan.morozyk.cookbook.redux.Store
import com.gmail.ivan.morozyk.cookbook.redux.base.Action
import com.gmail.ivan.morozyk.cookbook.redux.base.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceiptListMiddleware(private val receiptService: ReceiptService) : Middleware {
    override fun apply(action: Action, state: State) {
        when (action) {
            LoadReceiptList -> {
                launch {
                    val receiptListDto = withContext(Dispatchers.IO) {
                        receiptService.getReceipts(state.receiptListState.query).get()
                    }

                    Store.dispatch(ReceiptListLoaded(receiptListDto.results))
                }
            }
            else -> return
        }
    }
}