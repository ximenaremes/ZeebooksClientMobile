import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class PieChartView(context: Context) : View(context) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val sections: MutableList<Section> = mutableListOf()

    data class Section(val value: Float, val color: Int)

    fun setData(sections: List<Section>) {
        this.sections.clear()
        this.sections.addAll(sections)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = minOf(width, height) / 2f

        var startAngle = -90f

        for (section in sections) {
            val sweepAngle = (section.value / getTotalValue()) * 360f

            paint.color = section.color
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle,
                sweepAngle,
                true,
                paint
            )

            startAngle += sweepAngle
        }
    }

    private fun getTotalValue(): Float {
        var totalValue = 0f
        for (section in sections) {
            totalValue += section.value
        }
        return totalValue
    }
}