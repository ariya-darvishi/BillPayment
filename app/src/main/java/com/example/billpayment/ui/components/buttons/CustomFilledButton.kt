package com.example.billpayment.ui.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.billpayment.R
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.primary
import com.example.billpayment.ui.theme.surface1
import com.example.billpayment.utils.Dimens
import com.example.billpayment.utils.Dimens._12
import com.example.billpayment.ui.theme.font_14
import com.example.billpayment.ui.theme.font_20
import com.example.billpayment.ui.theme.iransanseFamily

@Composable
fun CustomFilledButtons(
    btnText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textColor: Color = primary,
    fontSize: TextUnit = font_14,
    fontWeight: FontWeight = FontWeight.Medium,
    style: TextStyle = Typography.labelLarge,
    lineHeight: TextUnit = font_20,
    backgroundBtnColor: Color = surface1,
) {
    FilledTonalButton(
        onClick = { onClick.invoke() },
        modifier = modifier,
        shape = RoundedCornerShape(_12),
        colors = ButtonDefaults.buttonColors(backgroundBtnColor)

    ) {
        Text(
            text = btnText,
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            style = style,
            lineHeight = lineHeight,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomFilledButtonsPreview() {
    CustomFilledButtons(
        btnText = stringResource(R.string.test_message),
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens._50)
            .padding(Dimens._5),
    )
}
