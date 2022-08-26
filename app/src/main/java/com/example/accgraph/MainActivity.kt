package com.example.accgraph

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart()
        addEntry(Random.nextDouble(-5.0, 5.0))
    }

    private fun addEntry(acc: Double) {
        i++
        val data = acc_line_chart.data
        val set = createSet()
        data.addDataSet(set)

        data.addEntry(Entry(i.toFloat(), acc.toFloat()), 0)
        data.notifyDataChanged()
        acc_line_chart.notifyDataSetChanged()
        acc_line_chart.setVisibleXRangeMaximum(100f)
        acc_line_chart.moveViewToX(data.entryCount.toFloat())

        Handler(Looper.getMainLooper()).postDelayed({ addEntry(Random.nextDouble(-5.0, 5.0)) }, 50)
    }

    private fun chart() {
        acc_line_chart.isDragEnabled = true
        acc_line_chart.setBackgroundColor(Color.WHITE)
        val data = LineData()
        data.setValueTextColor(Color.BLACK)
        acc_line_chart.data = data
    }

    private fun createSet(): LineDataSet {
        val set = LineDataSet(null, "Acc Round Data")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.lineWidth = 1f
        set.color = Color.BLUE
        set.mode = LineDataSet.Mode.LINEAR
        set.cubicIntensity = 0.1f
        return set
    }
}