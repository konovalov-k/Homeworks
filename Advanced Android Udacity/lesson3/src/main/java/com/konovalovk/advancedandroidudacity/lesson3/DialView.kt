package com.konovalovk.advancedandroidudacity.lesson3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

//Todo: 1.1 Use JvmOverloads
class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var radius = 0.0f                   // Radius of the circle.
    private var fanSpeed = FanSpeed.OFF         // The active selection.
    // position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    //Todo: 1.2 Create Paint obj
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    //Todo: 1.4 Calculates the X, Y coordinates on the screen for the text label and current indicator (0, 1, 2, or 3), given the current FanSpeed position and radius of the dial
    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    //Todo: 1.3 Calculate the size for the custom view's dial.
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    //Todo: 1.5 Render the view on the screen with the Canvas and Paint classes
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawMainCircle(canvas)
        drawIndicator(canvas)
        drawLabels(canvas)
    }

    //Todo: 1.6 Other drawing methods

    private fun drawMainCircle(canvas: Canvas){
        // Set dial background color to green if selection not off.
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        // Draw the dial. This method uses the current view width and height to find the center of the circle, the radius of the circle, and the current paint color.
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }

    //Calculate the X,Y coordinates for the indicator center based on the current fan speed.
    private fun drawIndicator(canvas: Canvas) {
        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius / 12, paint)
    }

    //Get the position for each label, and reuses the pointPosition object each time to avoid allocations
    private fun drawLabels(canvas: Canvas){
        // Draw the text labels.
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }
}