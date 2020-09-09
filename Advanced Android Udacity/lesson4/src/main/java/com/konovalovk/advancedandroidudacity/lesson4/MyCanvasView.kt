package com.konovalovk.advancedandroidudacity.lesson4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat

private const val STROKE_WIDTH = 12f // has to be float
//Todo: 1.1 Create MyCanvasView
class MyCanvasView(context: Context) : View(context) {
    //Todo: 1.3 Init some Canvas & Bitmap
    private var extraBitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    private var extraCanvas: Canvas = Canvas(extraBitmap)

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    //Todo: 1.7 Add Path
    private var path = Path() // Store the path that is being drawn when following the user's touch on the screen.

    //Todo: 1.8, 1.10 For touch events. Cache values
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    private var currentX = 0f
    private var currentY = 0f

    //Todo: 1.11 Touch Cinfiguration
    //interpolate a path between points for much better performance.
    //touch can wander before the system thinks the user is scrolling.
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

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

    //Todo: 1.9 Touch events handler
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    //Reset the path, move to the x-y coordinates of the touch event (motionTouchEventX and motionTouchEventY)
    //and assign currentX and currentY to that value.
    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    //Todo: 1.12 When touch proceed
    private fun touchMove() {
        //First we calculate the distance that has been moved, that's dx dy.
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        //If the movement was further than the touchTolerance
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            // Add a segment to the path.
            // Using quadTo, instead of line two, create a smoothly drawn line without corners.
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            //set the starting point for the next segment to the end point of this segment.
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }
}