package com.example.billpayment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.billpayment.ui.home.HomeViewModel

@Composable
fun HomeNavigation() {
    val viewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Navigation.Routes.HOME
    ) {
        composable(
            route = Navigation.Routes.HOME
        ) {
            HomeScreenDestination(navController, viewModel)
        }
    }
}

object Navigation {

    object Routes {
        const val HOME = "home"
    }

}
