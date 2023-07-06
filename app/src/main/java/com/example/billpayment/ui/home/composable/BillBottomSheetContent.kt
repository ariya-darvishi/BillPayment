package com.example.billpayment.ui.home.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import com.example.billpayment.ui.theme.font_20
import com.example.billpayment.ui.theme.onSurface
import com.example.billpayment.ui.theme.onSurfaceVariant
import com.example.billpayment.ui.theme.white
import com.example.billpayment.R
import com.example.billpayment.ui.components.RoundedCutoutShape
import com.example.billpayment.ui.components.buttons.CustomFilledButtons
import com.example.billpayment.ui.components.divider.DrawDashShape
import com.example.billpayment.ui.components.textFields.CustomFieldBodyTextField
import com.example.billpayment.ui.components.textFields.CustomPageTitleTextField
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_16
import com.example.billpayment.ui.theme.font_24
import com.example.billpayment.ui.theme.orange2
import com.example.billpayment.ui.theme.outlineVariant
import com.example.billpayment.ui.theme.surface1
import com.example.billpayment.utils.Dimens._1
import com.example.billpayment.utils.Dimens._10
import com.example.billpayment.utils.Dimens._90
import com.example.billpayment.utils.Dimens._12
import com.example.billpayment.utils.Dimens._15
import com.example.billpayment.utils.Dimens._16
import com.example.billpayment.utils.Dimens._18
import com.example.billpayment.utils.Dimens._2
import com.example.billpayment.utils.Dimens._22
import com.example.billpayment.utils.Dimens._25


@SuppressLint("SuspiciousIndentation")
@Composable
fun BillBottomSheetContent(onPaymentClick: () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Box(
            Modifier
                .fillMaxWidth()
                .background(surface1)
                .wrapContentHeight()
                .padding(top = _25, start = _22, end = _22),

            ) {
            var separatorOffsetY by remember { mutableStateOf<Float?>(null) }
            val cornerRadius = _15

            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = _16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CustomPageTitleTextField(stringResource(id = R.string.bill_report))


                Spacer(
                    modifier = Modifier.height(_12)
                )

                Card(
                    shape = RoundedCutoutShape(separatorOffsetY, cornerRadius),
                    colors = CardDefaults.cardColors(
                        containerColor = white,
                    ),
                    modifier = Modifier.padding(_10)

                ) {
                    Row(
                        modifier = Modifier.border(
                            width = _1,
                            color = outlineVariant,
                            shape = RoundedCutoutShape(separatorOffsetY, cornerRadius)
                        ),
                    ) {
                        Box(
                            modifier = Modifier
                                .height(_90)
                                .align(CenterVertically),
                            contentAlignment = Center
                        ) {
                            Column(
                                modifier = Modifier.padding(_12),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.aligned(CenterVertically)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_user),
                                    contentDescription = "description",
                                )
                                CustomFieldBodyTextField(stringResource(R.string.mobile_bill))

                            }
                        }
                        Box(

                            Modifier
                                .requiredHeight(_90)
                                .width(_2)
                                .background(Color.Gray, shape = DrawDashShape(step = _12))
                                .onGloballyPositioned {
                                    separatorOffsetY = it.boundsInParent().center.x
                                }

                        ) {}
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(_90),
                            contentAlignment = Center
                        ) {
                            Column(
                                modifier = Modifier.padding(_12),
                                verticalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Row() {

                                    CustomFieldBodyTextField(stringResource(R.string.bill_id_title))

                                    Spacer(Modifier.weight(1f))


                                    CustomFieldBodyTextField(stringResource(R.string.bill_id_test))
                                }

                                Spacer(Modifier.weight(1f))

                                Row() {

                                    CustomFieldBodyTextField(stringResource(R.string.bill_payment_title))

                                    Spacer(Modifier.weight(1f))


                                    CustomFieldBodyTextField(stringResource(R.string.bill_payment_test))

                                }

                            }
                        }
                    }
                }

                Card(
                    shape = RoundedCornerShape(_12), colors = CardDefaults.cardColors(
                        containerColor = white,
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .height(_90)
                        .padding(_10)
                        .border(
                            width = _1, color = outlineVariant, shape = RoundedCornerShape(_12)
                        )

                ) {

                    Box(
                        modifier = Modifier.height(_90),
                        contentAlignment = Center,

                        ) {
                        Row(
                            modifier = Modifier.padding(_10),
                        ) {

                            Text(
                                text = stringResource(R.string.amount_owed_title),
                                color = onSurfaceVariant,
                                fontSize = font_16,
                                fontWeight = FontWeight.Normal,
                                style = Typography.bodySmall,
                                lineHeight = font_20,
                            )

                            Spacer(Modifier.weight(1f))

                            Text(
                                text = stringResource(R.string.test_amount_owed),
                                color = onSurface,
                                fontSize = font_20,
                                fontWeight = FontWeight.Medium,
                                style = Typography.titleLarge,
                                lineHeight = font_24,
                            )

                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(_18)
                )
                CustomFilledButtons(
                    btnText = stringResource(R.string.bill_payment_title),
                    onClick = {
                        onPaymentClick.invoke()
                    },
                    backgroundBtnColor = orange2,
                    textColor = white,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}

