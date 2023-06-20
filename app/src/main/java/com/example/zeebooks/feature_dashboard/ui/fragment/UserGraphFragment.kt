package com.example.zeebooks.feature_dashboard.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentUserGraphBinding
import dagger.hilt.android.AndroidEntryPoint
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.BubbleChartView
import lecho.lib.hellocharts.view.LineChartView

@AndroidEntryPoint
class UserGraphFragment : BaseFragment<FragmentUserGraphBinding>() {

    override val resId = R.layout.fragment_user_graph

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLineChart()
        setup()

    }

    private fun setupLineChart() {
        val lineChartView = view?.findViewById<LineChartView>(R.id.lineChartView)

        val lines = mutableListOf<Line>()
        val values = mutableListOf<PointValue>()

        values.add(PointValue(0f, 10f))
        values.add(PointValue(1f, 5f))
        values.add(PointValue(2f, 10f))
        values.add(PointValue(3f, 12f))

        val line = Line(values)
//        line.color = R.color.text
        line.setColor(Color.BLUE)
        line.setHasPoints(true)
        lines.add(line)

        val axisX = Axis()
        axisX.values = listOf(
            AxisValue(0f).setLabel("Ianuarie"),
            AxisValue(1f).setLabel("Februarie"),
            AxisValue(2f).setLabel("Martie"),
            AxisValue(3f).setLabel("Aprilie")
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

    private fun setup(){
        val bubbleChartView = view?.findViewById<BubbleChartView>(R.id.bubbleChartView)

// Configurare date pentru Bubble Chart
        val bubbles = mutableListOf<BubbleValue>()

        bubbles.add(BubbleValue(0f, 5f, 10f).setColor(ChartUtils.COLOR_BLUE))
        bubbles.add(BubbleValue(1f, 8f, 12f).setColor(ChartUtils.COLOR_GREEN))
        bubbles.add(BubbleValue(2f, 3f, 8f).setColor(ChartUtils.COLOR_RED))
        bubbles.add(BubbleValue(3f, 6f, 15f).setColor(ChartUtils.COLOR_RED))

// Configurare axe
        val axisX = Axis()
        val axisY = Axis()

// Creare obiect BubbleChartData și configurare date și axe
        val bubbleChartData = BubbleChartData(bubbles)
        bubbleChartData.setBubbleScale(2.0f)
        bubbleChartData.setHasLabelsOnlyForSelected(true)
        bubbleChartData.setHasLabels(true)
//        bubbleChartData.setValueShape(ValueShape.CIRCLE)
//        bubbleChartData.setMinMaxBubbleSize(1f, 20f)
        bubbleChartData.axisXBottom = axisX
        bubbleChartData.axisYLeft = axisY

// Setare dimensiuni și interacțiuni
        bubbleChartData.valueLabelTextSize = 16
        bubbleChartView?.bubbleChartData = bubbleChartData
        bubbleChartView?.isZoomEnabled = false
        bubbleChartView?.isInteractive = true
    }
}