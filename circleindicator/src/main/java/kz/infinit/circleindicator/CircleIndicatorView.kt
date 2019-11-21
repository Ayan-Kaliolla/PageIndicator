package kz.infinit.circleindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleIndicatorView : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var activeDotColor: Int = Color.WHITE
    private var fixedDotCount: Int = -1
    private var enableEndDotGradient: Boolean = false
    private var endDotGradientCount: Int = -1
    private var radius: Float = 8f
    private var size: Int = 0
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr) {
        init(context, attrs, 0)
    }

    fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.CircleIndicatorView, defStyleAttr, 0)
                .apply {
                    activeDotColor =
                        getColor(R.styleable.CircleIndicatorView_civ_dotColor, Color.WHITE)
                    getInt(R.styleable.CircleIndicatorView_civ_dotFixedCount, -1).let {
                        fixedDotCount = if (it <= 0) -1 else it
                    }
                    endDotGradientCount =
                        getInt(R.styleable.CircleIndicatorView_civ_endDotGradientCount, 0)
                    enableEndDotGradient = if (endDotGradientCount > 0) true else false
                    radius = getDimension(R.styleable.CircleIndicatorView_civ_dotRadius, 8f)

                    paint.color = activeDotColor
                    paint.style = Paint.Style.FILL

                    recycle()
                }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getModeSize(
            MeasureSpec.getMode(widthMeasureSpec),
            MeasureSpec.getSize(widthMeasureSpec),
            ((radius * 2) * Math.abs(fixedDotCount) + paddingStart + paddingEnd).toInt()
        )

        val height = getModeSize(
            MeasureSpec.getMode(heightMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec),
            ((radius * 2) + paddingTop + paddingBottom).toInt()
        )
        size = ((radius * 2) + paddingStart + paddingEnd).toInt()
        centerX = (width / 2f)
        centerY = (height / 2f)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        repeat(fixedDotCount) {
            val index = Math.abs(it)
            val x = if (index % 2 == 0) {
                centerX - (index * size)
            } else {
                centerX + (index * size) + size
            }
            drawDot(canvas, x, centerY)
        }
    }

    private fun drawDot(canvas: Canvas, x: Float, y: Float) {
        canvas.drawCircle(x, y, radius, paint)
    }

    private fun getModeSize(mode: Int, size: Int, desiredSize: Int) = when (mode) {
        MeasureSpec.EXACTLY -> size
        MeasureSpec.AT_MOST -> Math.min(desiredSize, size)
        else -> desiredSize
    }
}