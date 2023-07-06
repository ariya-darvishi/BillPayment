package com.example.billpayment.ui.components.textFields

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_12
import com.example.billpayment.ui.theme.font_14
import com.example.billpayment.ui.theme.onSurface

@Composable
fun CustomFieldBodyTextField(text: String) {
    Text(
        text = text,
        color = onSurface,
        fontSize = font_12,
        fontWeight = FontWeight.Normal,
        style = Typography.bodySmall,
        lineHeight = font_14,
    )
}