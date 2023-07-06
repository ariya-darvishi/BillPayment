package com.example.billpayment.ui.components.textFields

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import com.example.billpayment.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextFieldColors(): androidx.compose.material3.TextFieldColors =
    TextFieldDefaults.textFieldColors(
        containerColor = white,
        cursorColor = Color.Black,
        disabledLabelColor = white,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )


