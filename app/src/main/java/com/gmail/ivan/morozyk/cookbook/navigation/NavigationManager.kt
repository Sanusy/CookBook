package com.gmail.ivan.morozyk.cookbook.navigation

import androidx.navigation.NavController

class NavigationManager {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController) {
        this.navController = navController
    }

    fun detachNavController() {
        navController = null
    }

    fun navigate(route: Routes) {
        navController?.navigate(route.name)
    }
}