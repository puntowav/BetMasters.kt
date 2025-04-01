package com.example.betmasters

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Estadisticas : AppCompatActivity() {

    private val WINS_KEY = intPreferencesKey("wins")
    private val LOSSES_KEY = intPreferencesKey("losses")
    private val BET_DATA_KEY = stringPreferencesKey("betData")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> tvTitle.setTextColor(Color.WHITE)
            else -> tvTitle.setTextColor(Color.BLACK)
        }

        val pieChart = findViewById<PieChart>(R.id.pieChart)
        val barChart = findViewById<BarChart>(R.id.barChart)

        CoroutineScope(Dispatchers.IO).launch {
            val preferences = dataStore.data.first()
            val wins = preferences[WINS_KEY] ?: 0
            val losses = preferences[LOSSES_KEY] ?: 0

            val pieEntries = arrayListOf(
                PieEntry(wins.toFloat(), "Victorias"),
                PieEntry(losses.toFloat(), "Derrotas")
            )

            val pieDataSet = PieDataSet(pieEntries, "Estadísticas").apply {
                val colorBlue = Color.parseColor("#2196F3")
                val colorGold = Color.parseColor("#FFD700")
                colors = listOf(colorBlue, colorGold)
                valueTextSize = 18f
            }
            val pieData = PieData(pieDataSet)

            withContext(Dispatchers.Main) {
                val legend = pieChart.legend
                legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                legend.textSize = 14f

                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        legend.textColor = Color.WHITE
                        pieDataSet.valueTextColor = Color.WHITE
                        pieChart.setHoleColor(Color.BLACK)
                        pieChart.setTransparentCircleColor(Color.BLACK)
                    }
                    else -> {
                        legend.textColor = Color.BLACK
                        pieDataSet.valueTextColor = Color.BLACK
                        pieChart.setHoleColor(Color.WHITE)
                        pieChart.setTransparentCircleColor(Color.WHITE)
                    }
                }
                pieChart.description.isEnabled = false
                pieChart.data = pieData
                pieChart.invalidate()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val preferences = dataStore.data.first()
            val betDataJson = preferences[BET_DATA_KEY] ?: "{}"
            val jsonObject = JSONObject(betDataJson)

            val dates = mutableListOf<String>()
            for (key in jsonObject.keys()) {
                dates.add(key)
            }
            dates.sort()

            val barEntries = ArrayList<BarEntry>()
            dates.forEachIndexed { index, date ->
                val amount = jsonObject.optDouble(date, 0.0)
                barEntries.add(BarEntry(index.toFloat(), amount.toFloat()))
            }

            val barDataSet = BarDataSet(barEntries, "Dinero Apostado").apply {
                color = Color.parseColor("#2196F3")
                valueTextSize = 14f
                valueTextColor = Color.BLACK
            }
            val barData = BarData(barDataSet)

            withContext(Dispatchers.Main) {
                barChart.xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val index = value.toInt()
                        return if (index in dates.indices) dates[index] else ""
                    }
                }
                barChart.xAxis.granularity = 1f
                barChart.xAxis.textSize = 12f

                barChart.axisLeft.textSize = 12f
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        barChart.axisLeft.textColor = Color.WHITE
                        barDataSet.valueTextColor = Color.WHITE
                        barChart.xAxis.textColor = Color.WHITE
                    }
                    else -> {
                        barChart.axisLeft.textColor = Color.BLACK
                        barDataSet.valueTextColor = Color.BLACK
                        barChart.xAxis.textColor = Color.BLACK
                    }
                }
                barChart.axisRight.isEnabled = false

                barChart.description.isEnabled = false
                barChart.data = barData
                barChart.invalidate()
            }
        }
    }
}