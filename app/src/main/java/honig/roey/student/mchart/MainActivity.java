package honig.roey.student.mchart;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PointDataSet> pointDataSets = new ArrayList<>();
    private List<BarEntry> entries = new ArrayList<>();
    private BarDataSet set;
    private BarData barData;
    private BarChart chart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LineChart is initialized from xml
        chart =  findViewById(R.id.chart);
        // style
        //chart.getXAxis().setTextSize(20); // in dp units


        // .. and more styling options
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setPinchZoom(true); // zooming X & Y Axis at one gesture

        setInitial_Bars_Values();
        addDataToChart();
        setBarChart(set);

        FloatingActionButton refresh = findViewById(R.id.floatingActionButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateY_Value(0, 7.2f);
                //appendNewY_Value(5);

            }
        });


    }

    private void setBarChart(BarDataSet set) {
        barData = new BarData(set);
        barData.setBarWidth(0.9f); // set custom bar width
        chart.setData(barData);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh
    }

    /*
    private void updateY_Value(int position, float newY) {
        entries.get(position).setY(newY);
        chart.invalidate(); // refresh
    }
    */


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

        pointDataSets.add(new PointDataSet(5f,7f));
    }
}
