package com.example.billpayment.ui.components.textFields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_16
import com.example.billpayment.ui.theme.font_20
import com.example.billpayment.ui.theme.onSurfaceVariant
import com.example.billpayment.utils.Dimens._4

@Composable
fun CustomFieldTitleTextField(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = _4),
        textAlign = TextAlign.Start,
        color = onSurfaceVariant,
        fontSize = font_16,
        fontWeight = FontWeight.Normal,
        style = Typography.titleLarge,
        lineHeight = font_20,
    )
}