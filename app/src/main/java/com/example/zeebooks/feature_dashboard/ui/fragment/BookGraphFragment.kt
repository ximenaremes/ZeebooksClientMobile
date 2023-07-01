package com.example.zeebooks.feature_dashboard.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentBookGraphBinding
import dagger.hilt.android.AndroidEntryPoint
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.view.ColumnChartView


@AndroidEntryPoint
class BookGraphFragment : BaseFragment<FragmentBookGraphBinding>() {

    override val resId = R.layout.fragment_book_graph

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBarChart()
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

            axisValues.add(AxisValue(i.toFloat()).setLabel("202${i}"))
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