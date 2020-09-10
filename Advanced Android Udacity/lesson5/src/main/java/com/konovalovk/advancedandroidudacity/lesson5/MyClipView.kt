package com.konovalovk.advancedandroidudacity.lesson5

import android.content.Context
import android.graphics.*
import android.os.Build
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

    //Todo: 1.12 Add rectF
    private var rectF = RectF(
        rectInset,
        rectInset,
        clipRectRight - rectInset,
        clipRectBottom - rectInset
    )

    //Todo: 1.18 Add rejectRow
    private val rejectRow = rowFour + rectInset + 2*clipRectBottom

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
        drawQuickRejectExample(canvas)
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

    //todo: 1.8 Implement subtract
    /**
     * The code does the following:
     * Save the canvas.
     * Translate the origin of the canvas into open space to the first row, second column, to the right of the first rectangle.
     * Apply two clipping rectangles. Subtracts the second rectangle from the first one.
     * Call the drawClippedRectangle() method to draw the modified canvas.
     * Restore the canvas state.
     * */
    private fun drawDifferenceClippingExample(canvas: Canvas) {
        canvas.save()
        // Move the origin to the right for the next rectangle.
        canvas.translate(columnTwo,rowOne)
        // Use the subtraction of two clipping rectangles to create a frame.
        canvas.clipRect(
            2 * rectInset,2 * rectInset,
            clipRectRight - 2 * rectInset,
            clipRectBottom - 2 * rectInset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .DIFFERENCE) was deprecated in API level 26. The recommended
        // alternative method is clipOutRect(float, float, float, float),
        // which is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            canvas.clipRect(
                4 * rectInset,4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset,
                Region.Op.DIFFERENCE
            )
        else {
            canvas.clipOutRect(
                4 * rectInset,4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    //Todo: 1.9 Implement subtract Circle
    private fun drawCircularClippingExample(canvas: Canvas) {
        canvas.save()
        canvas.translate(columnOne, rowTwo)
        // Clears any lines and curves from the path but unlike reset(),
        // keeps the internal data structure for faster reuse.
        path.rewind()
        path.addCircle(
            circleRadius,clipRectBottom - circleRadius,
            circleRadius,Path.Direction.CCW
        )
        // The method clipPath(path, Region.Op.DIFFERENCE) was deprecated in
        // API level 26. The recommended alternative method is
        // clipOutPath(Path), which is currently available in
        // API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    //Todo: 1.10 Implement drawIntersectionClippingExample
    private fun drawIntersectionClippingExample(canvas: Canvas) {
        canvas.save()
        canvas.translate(columnTwo,rowTwo)
        canvas.clipRect(
            clipRectLeft,
            clipRectTop,
            clipRectRight - smallRectOffset,
            clipRectBottom - smallRectOffset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .INTERSECT) was deprecated in API level 26. The recommended
        // alternative method is clipRect(float, float, float, float), which
        // is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipRect(
                clipRectLeft + smallRectOffset,
                clipRectTop + smallRectOffset,
                clipRectRight,clipRectBottom,
                Region.Op.INTERSECT
            )
        } else {
            canvas.clipRect(
                clipRectLeft + smallRectOffset,
                clipRectTop + smallRectOffset,
                clipRectRight,clipRectBottom
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    //Todo: 1.11 Implement drawCombinedClippingExample
    private fun drawCombinedClippingExample(canvas: Canvas) {
        canvas.save()
        canvas.translate(columnOne, rowThree)
        path.rewind()
        path.addCircle(
            clipRectLeft + rectInset + circleRadius,
            clipRectTop + circleRadius + rectInset,
            circleRadius,Path.Direction.CCW
        )
        path.addRect(
            clipRectRight / 2 - circleRadius,
            clipRectTop + circleRadius + rectInset,
            clipRectRight / 2 + circleRadius,
            clipRectBottom - rectInset,Path.Direction.CCW
        )
        canvas.clipPath(path) //CustomPath clip!!!
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    /**
     * The addRoundRect() function takes a rectangle.
     * Values for the x and y values of the corner radius.
     * The direction to wind the round-rectangle's contour.
     * Path.Direction specifies how closed shapes (e.g. rects, ovals) are oriented when they are added to a path.
     * CCW stands for counter-clockwise.
     * */

    //Todo: 1.13 Implement rounded Rectangle
    private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
        canvas.save()
        canvas.translate(columnTwo,rowThree)
        path.rewind()
        path.addRoundRect(
            rectF,clipRectRight / 4,
            clipRectRight / 4,
            Path.Direction.CCW
        )
        canvas.clipPath(path)
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    //Todo: 1.14 Implement drawOutsideClippingExample
    private fun drawOutsideClippingExample(canvas: Canvas) {
        canvas.save()
        canvas.translate(columnOne,rowFour)
        canvas.clipRect(2 * rectInset,2 * rectInset,
            clipRectRight - 2 * rectInset,
            clipRectBottom - 2 * rectInset)
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    //Todo: 1.15 Implement drawTranslatedTextExample
    private fun drawTranslatedTextExample(canvas: Canvas) {
        canvas.save()
        paint.color = Color.GREEN
        // Align the RIGHT side of the text with the origin.
        paint.textAlign = Paint.Align.LEFT
        // Apply transformation to canvas.
        canvas.translate(columnTwo,textRow)
        // Draw text.
        canvas.drawText(context.getString(R.string.translated),
            clipRectLeft,clipRectTop,paint)
        canvas.restore()
    }

    //Todo: 1.16 Implement drawSkewedTextExample
    /**
     * When you use View classes provided by the Android system, the system clips views for you to minimize overdraw.
     * When you use custom View classes and override the onDraw() method, clipping what you draw becomes your responsibility.
     */
    private fun drawSkewedTextExample(canvas: Canvas) {
        canvas.save()
        paint.color = Color.YELLOW
        paint.textAlign = Paint.Align.RIGHT
        // Position text.
        canvas.translate(columnTwo, textRow)
        // Apply skew transformation.
        canvas.skew(0.2f, 0.3f) //distort it
        canvas.drawText(context.getString(R.string.skewed),
            clipRectLeft, clipRectTop, paint)
        canvas.restore()
    }
    //Todo: 1.17 Implement quick reject
    /**
     check whether a specified rectangle or path would lie completely outside the currently visible regions
     */
    private fun drawQuickRejectExample(canvas: Canvas) {
        val inClipRectangle = RectF(clipRectRight / 2,
            clipRectBottom / 2,
            clipRectRight * 2,
            clipRectBottom * 2)

        val notInClipRectangle = RectF(RectF(clipRectRight+1,
            clipRectBottom+1,
            clipRectRight * 2,
            clipRectBottom * 2))

        canvas.run {
            save()
            translate(columnOne, rejectRow)
            clipRect(clipRectLeft,clipRectTop,clipRectRight,clipRectBottom)
        }

        val isRejected = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) canvas.quickReject(inClipRectangle)
        else canvas.quickReject(inClipRectangle, Canvas.EdgeType.AA)

        if (isRejected) canvas.drawColor(Color.WHITE)
        else {
            canvas.drawColor(Color.BLACK)
            canvas.drawRect(inClipRectangle, paint
            )
        }
        canvas.restore()
    }
}