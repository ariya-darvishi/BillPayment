package com.example.billpayment.ui.home

import com.example.billpayment.ui.base.ViewEvent
import com.example.billpayment.ui.base.ViewSideEffect
import com.example.billpayment.ui.base.ViewState

class HomeContract {

    sealed class Event : ViewEvent {
        object BackButtonClicked : Event()
        object OnBillPaymentButtonClick : Event()
        data class OnBillInquiryButtonClick(val billId: String, val billPayment: String) : Event()
    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
        val throwable: Throwable?,
        val billId: String,
        val billPayment: String,
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