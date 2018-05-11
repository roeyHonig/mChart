package honig.roey.student.mchart;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PointDataSet> pointDataSets = new ArrayList<>();
    private List<Entry> entries = new ArrayList<Entry>();
    private LineChart chart;
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LineChart is initialized from xml
        chart = (LineChart) findViewById(R.id.chart);

        setInitial_XY_Values();
        addDataToChart();

        LineDataSet dataSet = new LineDataSet(entries, "Roey Regev"); // add entries to dataset
        dataSet.setColor(R.color.colorAccent);
        dataSet.setValueTextColor(R.color.colorPrimary); // styling, ...

        lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh

        FloatingActionButton refresh = findViewById(R.id.floatingActionButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateY_Value(0, 7.2f);

            }
        });


    }

    private void updateY_Value(int position, float newY) {
        entries.get(position).setY(newY);
        chart.invalidate(); // refresh
    }

    private void addDataToChart() {
        for (PointDataSet data : pointDataSets) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getxValue(), data.getyValue()));
        }
    }

    private void setInitial_XY_Values() {
        pointDataSets.add(new PointDataSet(0,0));
        pointDataSets.add(new PointDataSet(1,1));
        pointDataSets.add(new PointDataSet(2,3));
        pointDataSets.add(new PointDataSet(3,2));
        pointDataSets.add(new PointDataSet(4,4));
        pointDataSets.add(new PointDataSet(5,7));
    }
}
