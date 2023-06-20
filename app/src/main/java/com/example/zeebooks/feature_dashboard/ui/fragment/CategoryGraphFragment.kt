package com.example.zeebooks.feature_dashboard.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCategoryGraphBinding
import dagger.hilt.android.AndroidEntryPoint
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.view.ColumnChartView
import lecho.lib.hellocharts.view.PieChartView
import java.text.DecimalFormat

@AndroidEntryPoint
class CategoryGraphFragment : BaseFragment<FragmentCategoryGraphBinding>() {

    override val resId = R.layout.fragment_category_graph

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPieChart()
        setupBarChart()
    }


    private fun setupPieChart() {

        val pieChartView = view?.findViewById<PieChartView>(R.id.pieChartView)
        val legendLayout = view?.findViewById<LinearLayout>(R.id.legendLayout)

        val pieData = mutableListOf<SliceValue>()
        pieData.add(SliceValue(30f, Color.BLUE).setValue(30f))
        pieData.add(SliceValue(20f, Color.GREEN).setValue(20f))
        pieData.add(SliceValue(50f, Color.RED).setValue(50f))

        val colorToTextMap = HashMap<Int, String>()
        colorToTextMap[Color.BLUE] = "Blue"
        colorToTextMap[Color.GREEN] = "Green"
        colorToTextMap[Color.RED] = "Red"

        val pieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(false).centerText1FontSize = 20
        pieChartData.setHasLabelsOnlyForSelected(false)
        pieChartView?.pieChartData = pieChartData

        val sliceValues = pieChartData.values

        val totalValue = sliceValues.sumOf { it.value.toDouble() }
        val decimalFormat = DecimalFormat("##.#")

        for (i in sliceValues.indices) {
            val sliceValue = sliceValues[i]

            val sliceColor = sliceValue.color
            val legendItemView =
                LayoutInflater.from(requireContext()).inflate(R.layout.legend_item, null)

            val legendColorView = legendItemView.findViewById<View>(R.id.legendColorView)
            val legendLabelView = legendItemView.findViewById<TextView>(R.id.legendLabelView)
            val percentageValue = (sliceValue.value / totalValue * 100).toFloat()
            legendColorView.setBackgroundColor(sliceColor)
            sliceValue.setLabel("${decimalFormat.format(percentageValue)}%")
            val labelText = colorToTextMap[sliceColor]
            legendLabelView.text = labelText ?: ""

            legendLayout?.addView(legendItemView)
        }

    }

    private fun setupBarChart() {

        val columnChartView = view?.findViewById<ColumnChartView>(R.id.columnChartView)

        val columns = mutableListOf<Column>()
        val axisValues = mutableListOf<AxisValue>()

        val data = listOf(
            Pair(10f, Color.BLUE),
            Pair(20f, Color.GREEN),
            Pair(15f, Color.RED),
            Pair(12f, Color.YELLOW)
        )

        for (i in data.indices) {
            val value = data[i].first
            val color = data[i].second

            val values = mutableListOf<SubcolumnValue>()
            values.add(SubcolumnValue(value, color))

            val column = Column(values)
            column.setHasLabels(true)
            column.setHasLabelsOnlyForSelected(true)
            columns.add(column)

            axisValues.add(AxisValue(i.toFloat()).setLabel("Categorie ${i + 1}"))
        }

        val axisX = Axis(axisValues)
        val axisY = Axis().setHasLines(true)

        val columnChartData = ColumnChartData(columns)
        columnChartData.axisXBottom = axisX
        columnChartData.axisYLeft = axisY
        columnChartData.valueLabelTextSize = 8
        columnChartView?.columnChartData = columnChartData
        columnChartView?.isZoomEnabled = false
        columnChartView?.isInteractive = true

        val legendLayout = view?.findViewById<LinearLayout>(R.id.legendBarChartLayout)

        for (i in columns.indices) {
            val column = columns[i]
            val columnColor = column.values[0].color

            val legendItemView =
                LayoutInflater.from(requireContext()).inflate(R.layout.legend_item, null)

            val legendColorView = legendItemView.findViewById<View>(R.id.legendColorView)
            legendColorView.setBackgroundColor(columnColor)

            val legendLabelView = legendItemView.findViewById<TextView>(R.id.legendLabelView)
            legendLabelView.text = "Cat"

            legendLayout?.addView(legendItemView)
        }

    }
}
