package com.gmail.ivan.morozyk.cookbook.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gmail.ivan.morozyk.cookbook.navigation.NavigationManager
import com.gmail.ivan.morozyk.cookbook.navigation.Routes
import com.gmail.ivan.morozyk.cookbook.navigation.bottomNavItems
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
            Store.dispatch(LoadReceiptList)//not sure if this is the right place to dispatch initial action

            CookBookTheme {

                Scaffold(bottomBar = {
                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        bottomNavItems.forEach { screen ->
                            BottomNavigationItem(
                                icon = { Icon(screen.icon, contentDescription = null) },
                                label = { Text(stringResource(screen.titleRes)) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route.name } == true,
                                onClick = {
                                    navController.navigate(screen.route.name) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }) {
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

                            composable(Routes.COLLECTION.name) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Collections")
                                }
                            }

                            composable(Routes.CART.name) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Cart")
                                }
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

