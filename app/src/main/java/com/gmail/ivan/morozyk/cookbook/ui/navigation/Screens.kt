package com.gmail.ivan.morozyk.cookbook.ui.navigation

sealed class Screen(val route:String) {
    object ReceiptList: Screen("ReceiptList")
    class Receipt(receiptId: String) : Screen("Receipt/$receiptId")
}


