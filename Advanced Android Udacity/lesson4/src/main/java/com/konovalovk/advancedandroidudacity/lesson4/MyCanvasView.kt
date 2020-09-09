package com.konovalovk.advancedandroidudacity.lesson4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.res.ResourcesCompat

private const val STROKE_WIDTH = 12f // has to be float
//Todo: 1.1 Create MyCanvasView
class MyCanvasView(context: Context) : View(context) {
    //Todo: 1.3 Init some Canvas & Bitmap
    private var extraBitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    private var extraCanvas: Canvas = Canvas(extraBitmap)

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    //Todo: 1.6 Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        //Paint.style specifies if the primitive being drawn is filled, stroked, or both.
        style = Paint.Style.STROKE // default: FILL
        //Stroke join of paints.join specifies how lines and curve segments join a stroked path.
        strokeJoin = Paint.Join.ROUND // default: MITER
        //StrokeCap sets the shape of the end of the line to be a cap.
        //Paints.cap specifies how to the beginning and ending of stroked lines and path look.
        strokeCap = Paint.Cap.ROUND // default: BUTT
        //stroke width specifies the width of the stroke in pixels.
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }

    //Todo 1.4 Provide onSizeChanged. Fill bitmap and create canvas from it
    /**
     * This is called during layout when the size of this view has changed. If
     * the view was just added to the view hierarchy, it is called with the old
     * values of 0. The code determines the drawing bounds for the custom view.
     *
     * @param width    Current width of this view.
     * @param height    Current height of this view.
     * @param oldWidth Old width of this view.
     * @param oldHeight Old height of this view.
     */
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        extraBitmap.recycle() //to avoid memory leak
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    /**
     * Renders view content: an outer circle to serve as the "dial",
     * and a smaller black circle to server as the indicator.
     * The position of the indicator is based on fanSpeed.
     *
     * @param canvas The canvas on which the background will be drawn.
     */
    //Todo: 1.5 Draw bitmap on canvas
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }
}