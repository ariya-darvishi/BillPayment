package com.example.billpayment.ui.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class RoundedCutoutShape(
    private val offsetY: Float?,
    private val cornerRadiusDp: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(run path@{
        val cornerRadius = with(density) { cornerRadiusDp.toPx() }
        val rect = Rect(Offset.Zero, size)
        val mainPath = Path().apply {
            addRoundRect(RoundRect(rect, CornerRadius(cornerRadius)))
        }
        if (offsetY == null) return@path mainPath
        val cutoutPath = Path().apply {
            val circleSize = Size(cornerRadius, cornerRadius) * 2f
            val visiblePart = 0.25f
            val leftOval = Rect(
                offset = Offset(
                    y = 0 - circleSize.height * (1 - visiblePart),
                    x = offsetY - circleSize.width / 2
                ), size = circleSize
            )
            val rightOval = Rect(
                offset = Offset(
                    y = rect.height - circleSize.height * visiblePart,
                    x = offsetY - circleSize.width / 2
                ), size = circleSize
            )
            addOval(leftOval)
            addOval(rightOval)
        }
        return@path Path().apply {
            op(mainPath, cutoutPath, PathOperation.Difference)
        }
    })
}