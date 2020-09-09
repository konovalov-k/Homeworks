package com.konovalovk.advancedandroidudacity.lesson3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    //Todo: 1.7 Provide FanSpeedChanges. Changes the current fan speed to the next speed in the list
    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
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
    /**
     * Computes the X/Y-coordinates for a label or indicator,
     * given the FanSpeed and radius where the label should be drawn.
     *
     * @param pos Position (FanSpeed)
     * @param radius Radius where label/indicator is to be drawn.
     * @return 2-element array. Element 0 is X-coordinate, element 1 is Y-coordinate.
     */
    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    //Todo: 1.10 Cache for attributes values
    private var fanSpeedLowColor = 0
    private var fanSpeedMediumColor = 0
    private var fanSeedMaxColor = 0

    //Todo: 1.8 Enables that view to accept user input.
    init {
        isClickable = true
        initStyledAttributes(attrs)
    }

    //Todo: 1.11 Get color from xml attributes
    private fun initStyledAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.DialView) {
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)
        }
    }


    //Todo: 1.3 Calculate the size for the custom view's dial.
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
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    //Todo: 1.5 Render the view on the screen with the Canvas and Paint classes
    /**
     * Renders view content: an outer circle to serve as the "dial",
     * and a smaller black circle to server as the indicator.
     * The position of the indicator is based on fanSpeed.
     *
     * @param canvas The canvas on which the background will be drawn.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawMainCircle(canvas)
        drawIndicator(canvas)
        drawLabels(canvas)
    }

    //Todo: 1.6 Other drawing methods
    private fun drawMainCircle(canvas: Canvas){
        // Set dial background color based on state
        paint.color = when (fanSpeed) {
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSeedMaxColor
        } as Int
        // Set dial background color to green if selection not off.
        //paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
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

    //Todo: 1.9 Add perform click method
    override fun performClick(): Boolean {
        if (super.performClick()) return true //must happen first, which enables accessibility events as well as calls onClickListener()

        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)

        invalidate() //Block UI. invalidates the entire view, forcing a call to onDraw() to redraw the view
        postInvalidate() //NotBlock UI
        return true
    }
}