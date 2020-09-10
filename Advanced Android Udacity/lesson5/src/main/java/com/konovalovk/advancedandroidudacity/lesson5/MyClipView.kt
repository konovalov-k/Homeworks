package com.konovalovk.advancedandroidudacity.lesson5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

//Todo: 1.1 Create Custom View
class ClippedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //Todo: 1.2 Add Paint
    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }

    //Todo: 1.3 Add path
    private val path = Path()

    //Todo: 1.4 Get dimens
    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)

    private val rectInset = resources.getDimension(R.dimen.rectInset)
    private val smallRectOffset = resources.getDimension(R.dimen.smallRectOffset)

    private val circleRadius = resources.getDimension(R.dimen.circleRadius)

    private val textOffset = resources.getDimension(R.dimen.textOffset)
    private val textSize = resources.getDimension(R.dimen.textSize)

    //Todo: 1.5 Provide table property
    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight

    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val textRow = rowFour + (1.5f * clipRectBottom)

    //Todo: 1.6 Provide onDraw
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackAndUnclippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
        // drawQuickRejectExample(canvas)
    }

    //todo: 1.7 Implement drawClippedRectangle&drawBackAndUnclippedRectangle
    private fun drawBackAndUnclippedRectangle(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)
        canvas.save()//save the canvas
        canvas.translate(columnOne,rowOne) //translate\move to the first row and column position,
        drawClippedRectangle(canvas)
        canvas.restore()//restore the canvas to its previous state.
    }

    private fun drawClippedRectangle(canvas: Canvas) {
        canvas.clipRect(//reduces the region of the screen that future draw operations can write to.
            clipRectLeft,clipRectTop,
            clipRectRight,clipRectBottom
        ) //set the boundaries of the clipping rectangle for the whole shape. Apply a clipping rectangle that constrains to drawing only the square.
        canvas.drawColor(Color.WHITE)
        paint.color = Color.RED
        canvas.drawLine(
            clipRectLeft,clipRectTop,
            clipRectRight,clipRectBottom,paint
        )

        paint.color = Color.GREEN
        canvas.drawCircle(
            circleRadius,clipRectBottom - circleRadius,
            circleRadius,paint
        )

        paint.run {
            color = Color.BLUE
            // Align the RIGHT side of the text with the origin.
            textSize = textSize
            textAlign = Paint.Align.RIGHT //specifies which side of the text to align to the origin, not which side of the origin the text goes,
        }

        canvas.drawText(
            context.getString(R.string.clipping),
            clipRectRight,textOffset,paint
        )
    }

    private fun drawDifferenceClippingExample(canvas: Canvas) {
    }
    private fun drawCircularClippingExample(canvas: Canvas) {
    }
    private fun drawIntersectionClippingExample(canvas: Canvas) {
    }
    private fun drawCombinedClippingExample(canvas: Canvas) {
    }
    private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
    }
    private fun drawOutsideClippingExample(canvas: Canvas) {
    }
    private fun drawTranslatedTextExample(canvas: Canvas) {
    }
    private fun drawSkewedTextExample(canvas: Canvas) {
    }
    private fun drawQuickRejectExample(canvas: Canvas) {
    }
}