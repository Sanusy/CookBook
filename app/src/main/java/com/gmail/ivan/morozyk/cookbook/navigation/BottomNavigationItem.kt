package com.gmail.ivan.morozyk.cookbook.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmail.ivan.morozyk.cookbook.R

sealed class BottomNavigationItem(
    val route: Routes,
    @StringRes val titleRes: Int,
    val icon: ImageVector,
) {
    object Search : BottomNavigationItem(
        Routes.RECEIPT_LIST,
        R.string.receipt_list_tab_title,
        Icons.Outlined.Search,
    )

    object Collection : BottomNavigationItem(
        Routes.COLLECTION,
        R.string.collection_tab_title,
        Icons.Outlined.BookmarkBorder,
    )

    object Cart : BottomNavigationItem(
        Routes.CART,
        R.string.cart_tab_title,
        Icons.Outlined.ShoppingCart,
    )
}

val bottomNavItems = listOf(
    BottomNavigationItem.Search,
    BottomNavigationItem.Collection,
    BottomNavigationItem.Cart
)
