package com.example.administrator.its.Util;

import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public class ChartUtils {
    public static int dayValue = 0;
    public static int weekValue = 1;
    public static int monthValue = 2;
    public static LineChart initChart(LineChart chart) {
        chart.getDescription().setEnabled(false);
        chart.setNoDataText("暂无数据");
        chart.setDrawGridBackground(true);
        chart.setBackgroundColor(4);
        chart.setScaleEnabled(true);
        chart.getAxisRight().setEnabled(false);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        chart.setExtraLeftOffset(0);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10f);
        xAxis.setGridColor(Color.parseColor("#30FFFFFF"));

        xAxis.setYOffset(-12);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawAxisLine(true);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setTextSize(12);
        yAxis.setXOffset(30);
        yAxis.setYOffset(-3);
        yAxis.setAxisMinimum(0);
        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f);
        chart.invalidate();
        return chart;
    }
    public static void setChartData(LineChart chart, List<Entry> values) {
        LineDataSet lineDataSet;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "");
            // 设置曲线颜色
            lineDataSet.setColor(Color.parseColor("#FF4081"));
            // 设置平滑曲线
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 显示坐标点的小圆点
            lineDataSet.setDrawCircles(true);
            // 显示坐标点的数据
            lineDataSet.setDrawValues(true);
            lineDataSet.setHighlightEnabled(false);

            LineData data = new LineData(lineDataSet);
            chart.setData(data);
            chart.invalidate();
        }
    }
    public static void notifyDataSetChanged(LineChart chart, List<Entry> values) {
        Log.e("2222",values.size()+"");
        setChartData(chart, values);
        chart.invalidate();
    }
}
