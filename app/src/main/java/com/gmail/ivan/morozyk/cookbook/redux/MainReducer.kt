package com.gmail.ivan.morozyk.cookbook.redux

import com.gmail.ivan.morozyk.cookbook.redux.base.Action
import com.gmail.ivan.morozyk.cookbook.redux.base.Reducer
import com.gmail.ivan.morozyk.cookbook.redux.receiptlist.ReceiptListReducer

class MainReducer : Reducer<State> {

    private val receiptListReducer = ReceiptListReducer()

    override fun reduce(state: State, action: Action): State = state.copy(
        receiptListState = receiptListReducer.reduce(
            state = state.receiptListState,
            action = action
        ),
    )
}