package com.example.billpayment.ui.components.textFields


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_12
import com.example.billpayment.ui.theme.font_16
import com.example.billpayment.ui.theme.onSurface

@Composable
fun CostumeFieldHintTextField( hint: String) {
    Text(
        text = hint,
        fontWeight = FontWeight.Normal,
        style = Typography.bodyMedium,
        textAlign = TextAlign.Start,
        color = onSurface,
        fontSize = font_12,
        lineHeight = font_16,
    )
}