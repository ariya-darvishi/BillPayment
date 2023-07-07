package com.example.billpayment.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.billpayment.R
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_12
import com.example.billpayment.ui.theme.font_16
import com.example.billpayment.ui.theme.onSurface
import com.example.billpayment.ui.theme.outline
import com.example.billpayment.ui.theme.outlineVariant
import com.example.billpayment.ui.theme.white
import com.example.billpayment.utils.Dimens
import com.example.billpayment.utils.Dimens._1
import com.example.billpayment.utils.Dimens._6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedButton(
    btnText: String,
    onClick: (() -> Unit)? = null,
    borderStroke: BorderStroke = BorderStroke(_1, outlineVariant),
    shape: Shape = RoundedCornerShape(_6),
    textColor: Color = onSurface,
    color: ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = outline, containerColor = white
    ),
    icon: Painter = painterResource(R.drawable.ic_left_arrow),
    fontSize: TextUnit = font_12,
    fontWeight: FontWeight = FontWeight.Normal,
    lineHeight: TextUnit = font_16,
    textStyle: TextStyle = Typography.bodySmall,
) {
    OutlinedButton(
        onClick = { onClick?.invoke() },
        border = borderStroke,
        shape = shape,
        colors = color,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {

                Text(
                    text = btnText,
                    style = textStyle,
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    lineHeight = lineHeight,
                )
                Icon(
                    painter = icon,
                    contentDescription = "",
                    modifier = Modifier
                        .width(Dimens._32)
                        .height(Dimens._15)
                )
            }
        }
    }
}