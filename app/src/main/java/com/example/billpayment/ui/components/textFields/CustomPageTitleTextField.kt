package com.example.billpayment.ui.components.textFields


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_22
import com.example.billpayment.ui.theme.font_28
import com.example.billpayment.ui.theme.onSurface

@Composable
fun CustomPageTitleTextField(title: String) {
    Text(
        text = title,
        fontSize = font_22,
        fontWeight = FontWeight.Normal,
        style = Typography.titleLarge,
        lineHeight = font_28,
        color = onSurface,
    )
}