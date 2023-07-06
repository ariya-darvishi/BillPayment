package com.example.billpayment.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.example.billpayment.ui.theme.font_14
import com.example.billpayment.ui.theme.font_20
import com.example.billpayment.ui.theme.inverseSurface
import com.example.billpayment.ui.theme.iransanseFamily
import com.example.billpayment.ui.theme.onInverseSurface
import com.example.billpayment.utils.Dimens._16
import com.example.billpayment.utils.Dimens._4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.billpayment.R


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ShowErrorSnackbar(message: String) {
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Box(
            modifier = Modifier
                .padding(top = _4, start = _16, end = _16)
        ) {
            coroutineScope.launch(Dispatchers.Main) {
                snackState.showSnackbar("")
            }
            SnackbarHost(
                modifier = Modifier.align(Alignment.TopCenter),
                hostState = snackState
            ) {
                CustomSnackBar(
                    message,
                    containerColor = inverseSurface
                )

            }
        }
    }
}

@Composable
fun CustomSnackBar(
    message: String,
    containerColor: Color,
) {
    androidx.compose.material3.Snackbar(containerColor = containerColor) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.Text(
                text = message,
                color = onInverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                fontSize = font_14,
                lineHeight = font_20,
                fontFamily = iransanseFamily,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 150, heightDp = 50)
@Composable
fun ShowErrorSnackbarPreview() {
    ShowErrorSnackbar(
        message = stringResource(R.string.test_message)
    )
}

