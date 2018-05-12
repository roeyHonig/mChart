package honig.roey.student.mchart;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PointDataSet> pointDataSets = new ArrayList<>();
    private List<BarEntry> entries = new ArrayList<>();
    private BarDataSet set;
    private BarData barData;
    private BarChart chart;
    // the labels that should be drawn on the XAxis
    private final String[] names = new String[] { "Roey Regev", "Roey Honig", "Idan Reshef", "Tal Efroni" };
    private IAxisValueFormatter formatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LineChart is initialized from xml
        chart =  findViewById(R.id.chart);
        // style
        //chart.getXAxis().setTextSize(20); // in dp units

        formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return names[(int) (value-1)];
            }
        };





        setInitial_Bars_Values();
        addDataToChart();
        setBarChart(set);

        FloatingActionButton refresh = findViewById(R.id.floatingActionButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateY_Value(1, 18f);
                //appendNewY_Value(5);

            }
        });







    }

    private void setBarChart(BarDataSet set) {
        barData = new BarData(set);
        barData.setBarWidth(0.9f); // set custom bar width
        chart.setData(barData);
        chart.setFitBars(true); // make the x-axis fit exactly all bars

        // .. and more styling options
        //chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setPinchZoom(true); // zooming X & Y Axis at one gesture

        // disable highlight of values by the user's gestures
        chart.setHighlightPerDragEnabled(false);
        chart.setHighlightPerTapEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisRight().setAxisMinimum(0f);

        // HighLight the Max Value
        chart.highlightValue(4,0);
        chart.invalidate(); // refresh
    }


    private void updateY_Value(int position, float newY) {
        entries.get(position).setY(newY);
        set = new BarDataSet(entries, "BarDataSet");
        setBarChart(set);
    }



    private void addDataToChart() {
        for (PointDataSet data : pointDataSets) {
            // turn your data into Entry objects
            entries.add(new BarEntry(data.getxValue(), data.getyValue()));
        }

        set = new BarDataSet(entries, "BarDataSet");
    }

    private void setInitial_Bars_Values() {
        // should be sorted
        pointDataSets.add(new PointDataSet(1f,1f));
        pointDataSets.add(new PointDataSet(2f,3f));
        pointDataSets.add(new PointDataSet(3f,2f));

        pointDataSets.add(new PointDataSet(4f,7f));
    }
}
