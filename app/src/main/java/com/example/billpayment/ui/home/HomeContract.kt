package com.example.billpayment.ui.home

import com.example.billpayment.ui.base.ViewEvent
import com.example.billpayment.ui.base.ViewSideEffect
import com.example.billpayment.ui.base.ViewState
import com.example.billpayment.utils.ValidateInput

class HomeContract {

    sealed class Event : ViewEvent {
        object BackButtonClicked : Event()
        object InsertInput : Event()
        object OnBillPaymentButtonClick : Event()
        data class OnBillInquiryButtonClick(val billId: String, val billPayment: String) : Event()
    }

    data class State(
        val isError: Boolean,
        val errorState: ValidateInput,
        val billID: String? = null,
        val paymentID: String? = null,
        val serviceName: String? = null,
        val serviceLogo: String? = null,
        ) : ViewState

    sealed class Effect : ViewSideEffect {

        sealed class BottomSheetVisibility : Effect() {
            object ShowBottomSheet : BottomSheetVisibility()
            object HideBottomSheet : BottomSheetVisibility()
        }

        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }

}