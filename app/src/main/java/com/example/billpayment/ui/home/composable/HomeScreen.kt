package com.example.billpayment.ui.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.billpayment.R
import com.example.billpayment.ui.base.SIDE_EFFECTS_KEY
import com.example.billpayment.ui.components.CustomToolbar
import com.example.billpayment.ui.components.buttons.CustomFilledButtons
import com.example.billpayment.ui.components.textFields.CustomPageTitleTextField
import com.example.billpayment.ui.home.HomeContract
import com.example.billpayment.ui.theme.orange2
import com.example.billpayment.ui.theme.surface1
import com.example.billpayment.ui.theme.white
import com.example.billpayment.utils.Dimens
import com.example.billpayment.utils.Dimens._10
import com.example.billpayment.utils.Dimens._12
import com.example.billpayment.utils.Dimens._20
import com.example.billpayment.utils.Dimens._24
import com.example.billpayment.utils.Dimens._28
import com.example.billpayment.utils.Dimens._55
import com.example.billpayment.utils.Dimens._8
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class,
)
@Composable
fun HomeScreen(
    state: HomeContract.State,
    effectFlow: Flow<HomeContract.Effect>?,
    onEventSent: (event: HomeContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: HomeContract.Effect.Navigation) -> Unit,
) {

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutine = rememberCoroutineScope()
    Column() {

        LaunchedEffect(SIDE_EFFECTS_KEY) {
            effectFlow?.onEach { effect ->
                when (effect) {
                    is HomeContract.Effect.Navigation.Back -> {
                        onNavigationRequested(HomeContract.Effect.Navigation.Back)
                    }

                    is HomeContract.Effect.BottomSheetVisibility.ShowBottomSheet -> {
                        bottomSheetVisibility(
                            coroutine, sheetState
                        )
                    }

                    is HomeContract.Effect.BottomSheetVisibility.HideBottomSheet -> {
                        bottomSheetVisibility(
                            coroutine, sheetState
                        )
                    }

                    else -> {}
                }
            }?.collect() {}
        }
        ModalBottomSheetLayout(sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = _28, topEnd = _28),
            sheetContent = {
                BillBottomSheetContent(onPaymentClick = { onEventSent(HomeContract.Event.OnBillPaymentButtonClick) })
            }

        ) {
            Column(
                modifier = Modifier
                    .background(surface1)
                    .fillMaxSize()
                    .padding(_24)
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                    CustomToolbar(
                        toolbarTitle = stringResource(R.string.bill_payment_title),
                        backBtnText = stringResource(R.string.back_title)
                    ) {
                        onEventSent(
                            HomeContract.Event.BackButtonClicked
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = _24),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            GlideImage(
                                modifier = Modifier
                                    .padding(start = _20, end = _20)
                                    .size(_55)
                                    .clip(RoundedCornerShape(Dimens._15))
                                    .background(white)
                                    .padding(_10),
                                model = R.drawable.ic_barcode,
                                contentDescription = "",
                            )

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(_8)
                            )

                            CustomPageTitleTextField(stringResource(R.string.bill_title))

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(_12)
                            )

                            CustomInputTextLayout(
                                title = stringResource(R.string.bill_id_title),
                                hint = stringResource(
                                    R.string.bill_id_hint
                                )
                            )

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(_8)
                            )

                            CustomInputTextLayout(
                                title = stringResource(R.string.payment_id), hint = stringResource(
                                    R.string.bill_id_hint
                                )
                            )
                        }

                        Box(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {

                            CustomFilledButtons(
                                btnText = stringResource(R.string.bill_report),
                                onClick = {
                                    onEventSent(
                                        HomeContract.Event.OnBillInquiryButtonClick(
                                            "sdfs", "drgdge"
                                        )
                                    )
                                },
                                backgroundBtnColor = orange2,
                                textColor = white,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        when {
                            state.isLoading -> {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            horizontal = Dimens._16, vertical = Dimens._32
                                        ), horizontalAlignment = Alignment.CenterHorizontally
                                ) {
//                            Loading()
                                }
                            }

                            state.isError -> state.throwable?.let {/* ErrorHandler(it)*/ }

                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun bottomSheetVisibility(coroutineScope: CoroutineScope, sheetState: ModalBottomSheetState) {
    coroutineScope.launch {
        if (sheetState.currentValue == ModalBottomSheetValue.Hidden) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }
}