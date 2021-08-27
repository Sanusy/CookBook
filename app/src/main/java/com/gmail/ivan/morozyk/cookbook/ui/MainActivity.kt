package com.gmail.ivan.morozyk.cookbook.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.ivan.morozyk.cookbook.navigation.NavigationManager
import com.gmail.ivan.morozyk.cookbook.navigation.Routes
import com.gmail.ivan.morozyk.cookbook.redux.ReduxScreen
import com.gmail.ivan.morozyk.cookbook.redux.Store
import com.gmail.ivan.morozyk.cookbook.redux.receiptlist.LoadReceiptList
import com.gmail.ivan.morozyk.cookbook.ui.receiptlist.ReceiptListConnector
import com.gmail.ivan.morozyk.cookbook.ui.receiptlist.ReceiptListScreen
import com.gmail.ivan.morozyk.cookbook.ui.theme.CookBookTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigationManager: NavigationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            navigationManager.attachNavController(navController)
            Store.dispatch(LoadReceiptList)//not shore if this is the right place to dispatch initial action

            CookBookTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.RECEIPT_LIST.name
                    ) {
                        composable(Routes.RECEIPT_LIST.name) {
                            ReduxScreen(connector = ReceiptListConnector()) { receiptListProps ->
                                ReceiptListScreen(props = receiptListProps)
                            }
                        }
                    }
                }
            }

            DisposableEffect(key1 = MainActivity::class.simpleName) {
                onDispose {
                    navigationManager.detachNavController()
                }
            }
        }
    }
}

