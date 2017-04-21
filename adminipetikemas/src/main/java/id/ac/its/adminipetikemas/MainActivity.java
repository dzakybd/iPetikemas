package id.ac.its.adminipetikemas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 1923));
        values.add(new PointValue(1, 2000));
        values.add(new PointValue(2, 1529));
        values.add(new PointValue(3, 1699));

        List<PointValue> value_peti = new ArrayList<PointValue>();
        value_peti.add(new PointValue(0, 345));
        value_peti.add(new PointValue(1, 445));
        value_peti.add(new PointValue(2, 532));
        value_peti.add(new PointValue(3, 253));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        Line peti = new Line(value_peti).setColor(Color.RED).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        lines.add(peti);
//        List<Line> lines2 = new ArrayList<Line>();
//        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        LineChartData data2 = new LineChartData();
        data2.setLines(lines);

        LineChartView chart = (LineChartView) findViewById(R.id.chart);
        chart.setLineChartData(data);
        LineChartView chart2 = (LineChartView) findViewById(R.id.chart2);
        chart2.setLineChartData(data2);



//
//        Line waktu = new Line(value_peti).setColor(Color.GREEN).setCubic(true);
//        List<Line> waktu2 = new ArrayList<Line>();
//        lines.add(waktu);
//
//        LineChartData data_waktu = new LineChartData();
//        data.setLines(waktu2);
//
//        LineChartView chart_waktu = (LineChartView) findViewById(R.id.chart2);
//        chart_waktu.setLineChartData(data_waktu);
    }
    }
