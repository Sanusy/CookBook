package com.gmail.ivan.morozyk.cookbook.redux

import com.gmail.ivan.morozyk.cookbook.redux.receiptlist.ReceiptListState

data class State(val receiptListState: ReceiptListState = ReceiptListState())
