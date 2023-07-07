package com.example.billpayment.ui.components.textFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.billpayment.ui.theme.Typography
import com.example.billpayment.ui.theme.font_12
import com.example.billpayment.ui.theme.font_16
import com.example.billpayment.ui.theme.iransanseFamily
import com.example.billpayment.ui.theme.onSurfaceVariant
import com.example.billpayment.ui.theme.outlineVariant
import com.example.billpayment.utils.Dimens
import com.example.billpayment.utils.Dimens._1
import com.example.billpayment.utils.Dimens._12

@Composable
fun CustomTextField(
    hint: String,
    shape: Shape = RoundedCornerShape(_12),
    textInputChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number, capitalization = KeyboardCapitalization.None
    ),
    colors: TextFieldColors = InputTextFieldColors(),
    onDeleteInputClick: () -> Unit,
) {
    var textInput by rememberSaveable { mutableStateOf("") }

    val maxLength = 13

    val customTextSelectionColors =
        TextSelectionColors(handleColor = onSurfaceVariant, backgroundColor = onSurfaceVariant)


    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        val focusManager = LocalFocusManager.current

        TextField(value = textInput,
            onValueChange = {
                if (it.length <= maxLength) {
                    textInput = it
                    textInputChange.invoke(it)
                }
            },
            placeholder = {
                CostumeFieldHintTextField(hint)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    _1, outlineVariant, shape
                ),
            colors = colors,

            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            shape = shape,
            trailingIcon = {
                if (textInput.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            textInput = ""
                            onDeleteInputClick.invoke()
                        }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
            })
    }

    Text(
        text = "${textInput.length} از $maxLength",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens._4),
        textAlign = TextAlign.End,
        color = onSurfaceVariant,
        fontSize = font_12,
        fontWeight = FontWeight.Normal,
        fontFamily = iransanseFamily,
        style = Typography.labelSmall,
        lineHeight = font_16,
    )
}