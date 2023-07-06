package com.example.billpayment.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.billpayment.ui.theme.BillPaymentTheme
import com.example.billpayment.ui.navigation.HomeNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BillPaymentTheme(darkTheme = false) {
                HomeNavigation()
            }
        }
    }
}