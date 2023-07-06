package com.example.billpayment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.billpayment.ui.home.HomeContract
import com.example.billpayment.ui.home.HomeViewModel
import com.example.billpayment.ui.home.composable.HomeScreen

@Composable
fun HomeScreenDestination(navController: NavController, viewModel: HomeViewModel) {

    HomeScreen(state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },

        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is HomeContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        })

}
