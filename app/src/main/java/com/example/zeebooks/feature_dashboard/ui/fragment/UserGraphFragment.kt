package com.example.zeebooks.feature_dashboard.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentUserGraphBinding
import dagger.hilt.android.AndroidEntryPoint
import lecho.lib.hellocharts.formatter.AxisValueFormatter
import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.view.LineChartView

@AndroidEntryPoint
class UserGraphFragment : BaseFragment<FragmentUserGraphBinding>() {

    override val resId = R.layout.fragment_user_graph

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLineChart()
    }

    private fun setupLineChart() {
        val lineChartView = view?.findViewById<LineChartView>(R.id.lineChartView)

        val lines = mutableListOf<Line>()
        val values = mutableListOf<PointValue>()

        values.add(PointValue(0f, 0f))
        values.add(PointValue(1f, 0f))
        values.add(PointValue(2f, 0f))
        values.add(PointValue(3f, 2f))
        values.add(PointValue(4f, 0f))

        val line = Line(values)
//        line.color = R.color.text
        line.setColor(Color.BLUE)
        line.setHasPoints(true)
        lines.add(line)

        val axisX = Axis()
        axisX.values = listOf(
            AxisValue(0f).setLabel("Martie"),
            AxisValue(1f).setLabel("Aprilie"),
            AxisValue(2f).setLabel("Mai"),
            AxisValue(3f).setLabel("Iunie"),
            AxisValue(4f).setLabel("Iulie"),
        )

        val axisY = Axis()
        val lineChartData = LineChartData(lines)
        lineChartData.axisXBottom = axisX
        lineChartData.axisYLeft = axisY

        lineChartData.valueLabelTextSize = 14
        lineChartView?.lineChartData = lineChartData
        lineChartView?.isZoomEnabled = false
        lineChartView?.isInteractive = true
    }

}