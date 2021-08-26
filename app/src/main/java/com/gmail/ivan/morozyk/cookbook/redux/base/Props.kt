package com.gmail.ivan.morozyk.cookbook.redux.base

import com.gmail.ivan.morozyk.cookbook.ui.receiptlist.ReceiptListProps

data class Props(val receiptListProps: ReceiptListProps = ReceiptListProps.Loading)

//@Composable
//fun Store.asState() = remember { mutableStateOf(this.props) }
