package com.gmail.ivan.morozyk.cookbook.redux.receiptlist

import com.gmail.ivan.morozyk.cookbook.data.dto.rest.ReceiptListDto
import com.gmail.ivan.morozyk.cookbook.redux.base.Action

object LoadReceiptList: Action
data class ReceiptListLoaded(val receiptList: List<ReceiptListDto.ReceiptListItem>) : Action