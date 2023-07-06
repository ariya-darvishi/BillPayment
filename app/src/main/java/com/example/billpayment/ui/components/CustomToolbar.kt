package com.example.billpayment.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.billpayment.ui.components.buttons.CustomOutlinedButton
import com.example.billpayment.ui.components.textFields.CustomPageTitleTextField


@Composable
fun CustomToolbar(toolbarTitle: String, backBtnText: String? = null, onBackBtnClick: () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomPageTitleTextField(toolbarTitle)

                Spacer(modifier = Modifier.weight(1f))

                backBtnText?.let { btnText -> CustomOutlinedButton(btnText = btnText, onClick = onBackBtnClick) }
            }
        }
    }
}
