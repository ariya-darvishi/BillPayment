package com.example.billpayment.ui.home.composable

import androidx.compose.runtime.Composable
import com.example.billpayment.ui.home.HomeContract
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    state: HomeContract.State,
    effectFlow: Flow<HomeContract.Effect>?,
    onEventSent: (event: HomeContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: HomeContract.Effect.Navigation) -> Unit,
) {


}
