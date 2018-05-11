package honig.roey.student.mchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PointDataSet> pointDataSets = new ArrayList<>();
    private List<Entry> entries = new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LineChart is initialized from xml
        LineChart chart = (LineChart) findViewById(R.id.chart);

        setInitial_XY_Values();
        addDataToChart();

        LineDataSet dataSet = new LineDataSet(entries, "Roey Regev"); // add entries to dataset
        dataSet.setColor(R.color.colorAccent);
        dataSet.setValueTextColor(R.color.colorPrimary); // styling, ...

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
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
