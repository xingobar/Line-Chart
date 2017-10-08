package com.example.administrator.linechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnChartGestureListener,
                                                                OnChartValueSelectedListener
{

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);
        lineChart = (LineChart)findViewById(R.id.linechart); // 初始化元件
        lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);

        setLineCharData();

        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            lineChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.d("LongPress","Chart long pressed");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.d("DoubleTapped","Chart double tapped");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.d("SingleTapped","Chart single tapped");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.d("Scale","ScaleX : " + scaleX + "  Scale Y : " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.d("Translate"," Translate X : " + dX + " Translate Y : " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.d(" Value Selected " , e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.d("Nothing Selected","Nothing selected");
    }

    private void setLineCharData()
    {
        ArrayList<String> xAxisList = setXAxisArrayList();
        ArrayList<Entry>  yAxisList = setYAxisArrayList();

        LineDataSet lineDataSet;

        lineDataSet = new LineDataSet(yAxisList,"DataSet 1");
        lineDataSet.setFillAlpha(110);

        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.argb(255,30,136,229));
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(xAxisList,dataSets);

        lineChart.setData(data);

    }

    private ArrayList<String> setXAxisArrayList()
    {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("10");
        xAxis.add("20");
        xAxis.add("25");
        xAxis.add("30.5");
        xAxis.add("35");
        return  xAxis;
    }

    private ArrayList<Entry> setYAxisArrayList()
    {
        ArrayList<Entry> yAxis = new ArrayList<Entry>();
        yAxis.add(new Entry(30,0));
        yAxis.add(new Entry(50,1));
        yAxis.add(new Entry(66.6f,2));
        yAxis.add(new Entry(88.5f,3));
        yAxis.add(new Entry(155.7f,4));

        return  yAxis;
    }
}
