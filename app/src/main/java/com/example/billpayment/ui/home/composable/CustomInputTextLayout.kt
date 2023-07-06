package com.example.billpayment.ui.home.composable


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.billpayment.ui.components.textFields.CustomFieldTitleTextField
import com.example.billpayment.ui.components.textFields.CustomTextField

@Composable
fun CustomInputTextLayout(title: String, hint: String) {

    Column {
        CustomFieldTitleTextField(title)
        CustomTextField(hint = hint)
    }
}