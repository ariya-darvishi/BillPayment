package com.example.billpayment.ui.home

import com.example.billpayment.ui.base.BaseViewModel
import com.example.billpayment.utils.ValidateInput
import com.example.billpayment.utils.isBillValid
import com.example.billpayment.utils.isPaymentValid
import com.example.billpayment.utils.padNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    override fun setInitialState() = HomeContract.State(
        isError = false,
        errorState = ValidateInput.NONE,
    )

    override fun handleEvents(event: HomeContract.Event) {

        when (event) {
            is HomeContract.Event.BackButtonClicked -> {
                setEffect { HomeContract.Effect.Navigation.Back }
            }

            is HomeContract.Event.OnBillInquiryButtonClick -> {
                genrateBill(event.billId, event.billPayment)
            }

            is HomeContract.Event.OnBillPaymentButtonClick -> {
                setEffect { HomeContract.Effect.BottomSheetVisibility.HideBottomSheet }
            }

            is HomeContract.Event.InsertInput -> {
                setState { copy(isError = false, errorState = ValidateInput.NONE) }
            }

            is HomeContract.Event.OnDeleteBillIDInputClick -> {
                setEffect { HomeContract.Effect.DeleteBillID }
            }

            is HomeContract.Event.OnDeletePaymentIDInputClick -> {
                setEffect { HomeContract.Effect.DeletePaymentID }
            }

            else -> {}
        }
    }

    private fun genrateBill(billID: String?, paymentID: String?) {
        if (!billID.isNullOrEmpty() && !paymentID.isNullOrEmpty()) {

//            val companyCode = billID.getCompanyCode()
//            val serviceCode = billID.getServiceCode()
//            val genratedBillId = billID.generateBillId(companyCode.toString(), serviceCode.toString())
            validateInput(billID, paymentID)

        } else {
            setState {
                copy(isError = true, errorState = ValidateInput.EMPTY_INPUT)
            }
        }

    }

    private fun validateInput(billId: String, billPayment: String) {
        setState { copy(isError = false, errorState = ValidateInput.NONE) }

        if (billId.isBillValid()) {
            if (billPayment.padNumber().isBillValid()) {
                paymentValidation(billId, billPayment)
            } else {
                setState {
                    copy(isError = true, errorState = ValidateInput.INVALID_PAYMENT_ID)
                }
            }
        } else {
            setState { copy(isError = true, errorState = ValidateInput.INVALID_BILL_ID) }
        }
    }

    private fun paymentValidation(billId: String, billPayment: String) {
        setState { copy(isError = false, errorState = ValidateInput.NONE) }
        when (isPaymentValid(billId, billPayment)) {
            true -> {
                setState { copy(billID = billId, paymentID = billPayment) }
                setEffect { HomeContract.Effect.BottomSheetVisibility.ShowBottomSheet }
            }

            false -> {
                setState { copy(isError = true, errorState = ValidateInput.INVALID_PAYMENT) }
            }
        }
    }
}