package com.example.finalexamapp.views

import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.apply {
            drawColor(Color.CYAN) // Background color
            drawText("Vue personnalisée!", 50f, 100f, paint) // Custom text
        }
    }
}
