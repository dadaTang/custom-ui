package com.xtan.component.dialog.mask

import android.graphics.RectF

data class HighlightArea(
        var areaRect: RectF = RectF(),
        var radiusX: Float = 0f,
        var radiusY: Float = 0f
)