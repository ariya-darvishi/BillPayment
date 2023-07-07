package com.example.billpayment.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.billpayment.R
import com.example.billpayment.ui.components.ShowErrorSnackbar


@Composable
fun ErrorHandler(invalidState: ValidateInput, function: (() -> Unit)? = null) {
    when (invalidState) {
        ValidateInput.INVALID_BILL_ID -> {
            ShowErrorSnackbar(stringResource(R.string.invalid_bill_id))
        }

        ValidateInput.INVALID_PAYMENT_ID -> {
            ShowErrorSnackbar(stringResource(R.string.invalid_payment_it_message))
        }

        ValidateInput.INVALID_PAYMENT -> {
            ShowErrorSnackbar(stringResource(R.string.invalid_payment_message))
        }

        ValidateInput.EMPTY_INPUT -> {
            ShowErrorSnackbar(stringResource(R.string.empty_input))
        }

        else -> {}

    }
}
