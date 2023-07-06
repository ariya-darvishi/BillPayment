package com.example.billpayment.ui.home

import com.example.billpayment.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    override fun setInitialState() = HomeContract.State(
        isLoading = true,
        isError = false,
        throwable = null,
        billId = "",
        billPayment = "",
    )

    override fun handleEvents(event: HomeContract.Event) {

        when (event) {
            is HomeContract.Event.BackButtonClicked -> {
                setEffect { HomeContract.Effect.Navigation.Back }
            }

            is HomeContract.Event.OnBillInquiryButtonClick -> {
                setEffect { HomeContract.Effect.BottomSheetVisibility.ShowBottomSheet }
            }

            is HomeContract.Event.OnBillPaymentButtonClick -> {
                setEffect { HomeContract.Effect.BottomSheetVisibility.HideBottomSheet }
            }
        }
    }
}