package com.gmail.ivan.morozyk.cookbook.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.ivan.morozyk.cookbook.redux.ReduxScreen
import com.gmail.ivan.morozyk.cookbook.ui.receiptlist.ReceiptListConnector
import com.gmail.ivan.morozyk.cookbook.ui.receiptlist.ReceiptListScreen
import com.gmail.ivan.morozyk.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            CookBookTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "ReceiptList") {
                        composable("ReceiptList") {
                            ReduxScreen(connector = ReceiptListConnector()) { receiptListProps ->
                                ReceiptListScreen(props = receiptListProps)
                            }
                        }
                    }
                }
            }
        }
    }
}

