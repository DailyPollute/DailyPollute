package com.github.dailypollute;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showWhoComparison();
//        showTrip();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showWhoComparison() {
        setContentView(R.layout.activity_home_page);
//        setContentView(R.layout.trip_to_kings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        StatisticalAnalysis statisticalAnalysis = new StatisticalAnalysis();
//        statisticalAnalysis.setDataPoints(dataPoints);
//        statisticalAnalysis.analyse();

        // Get data from analysis
        double pm2_5_24hMean = 10 + statisticalAnalysis.getRelativePm2_5_24hMean()*100;
        double pm2_5_annualMean = 20 + statisticalAnalysis.getRelativePm2_5_annualMean()*100;

        double pm10_24hMean = 30 + statisticalAnalysis.getRelativePm10_24hMean()*100;
        double pm10_annualMean = 20 + statisticalAnalysis.getRelativePm10_annualMean()*100;

        double o3_8hMean = 90 + statisticalAnalysis.getRelativeO3_8hMean()*100;

        double no2_1hMean = 90 + statisticalAnalysis.getRelativeNo2_1hMean()*100;
        double no2_annualMean = 40 +statisticalAnalysis.getRelativeNo2_annualMean()*100;


        //// PM 2.5
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("PM 2.5");
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0.5, pm2_5_24hMean),
                new DataPoint(1.5, pm2_5_annualMean)
        });
        graph.addSeries(series);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(2);

        // set manual X labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "24 hours", "", "1 year", ""});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);



        //// PM 10
        graph = (GraphView) findViewById(R.id.view);
        graph.setTitle("PM 10");
        series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0.5, pm10_24hMean),
                new DataPoint(1.5, pm10_annualMean)
        });
        graph.addSeries(series);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(2);

        // set manual X labels
        staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "24 hours", "", "1 year", ""});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);


        //// NO2
        graph = (GraphView) findViewById(R.id.view2);
        graph.setTitle("NO2");
        series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0.5, no2_1hMean),
                new DataPoint(1.5, no2_annualMean)
        });
        graph.addSeries(series);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(2);

        // set manual X labels
        staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "1 hour", "", "1 year", ""});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);
    }

    private void showTrip(){
        // WHO recommended guideline values (see http://www.who.int/mediacentre/factsheets/fs313/en/)
        double who_pm2_5_annualMean = 10;  // ug per m3
        double who_pm2_5_24hMean = 25;     // ug per m3

        double who_pm10_annualMean = 20;   // ug per m3
        double who_pm10_24hMean = 50;      // ug per m3

        double who_o3_8hMean = 100;        // ug per m3

        double who_no2_annualMean = 40;    // ug per m3
        double who_no2_1hMean = 200;       // ug per m3

        setContentView(R.layout.trip_to_kings);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        FetchData fetchData = new FetchData();
        List<PersonalPollutionDataPoint> dataPoints = fetchData.readData();
        int N_points = 11;

        //// PM 2.5
        GraphView graph = (GraphView) findViewById(R.id.view2);
        graph.setTitle("PM 2.5 (compared with WHO 24h mean)");

        DataPoint[] line1 = new DataPoint[N_points];
        DataPoint[] line2 = new DataPoint[N_points];

        for (int i=0; i<N_points; i++) {
            PersonalPollutionDataPoint dataPoint = dataPoints.get(i);
            line1[i] = new DataPoint(i+1, dataPoint.getPm2_5());
            line2[i] = new DataPoint(i+1, who_pm2_5_24hMean);
        };

        LineGraphSeries<DataPoint>  series = new LineGraphSeries<DataPoint>(line1);
        series.setDrawDataPoints(true);
        graph.addSeries(series);
        series = new LineGraphSeries<DataPoint>(line2);
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(N_points);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);



        //// PM 10
        graph = (GraphView) findViewById(R.id.view);
        graph.setTitle("PM 10 (compared with WHO 24h mean)");

        line1 = new DataPoint[N_points];
        line2 = new DataPoint[N_points];

        for (int i=0; i<N_points; i++) {
            PersonalPollutionDataPoint dataPoint = dataPoints.get(i);
            line1[i] = new DataPoint(i+1, dataPoint.getPm10());
            line2[i] = new DataPoint(i+1, who_pm10_24hMean);
        };

        series = new LineGraphSeries<DataPoint>(line1);
        series.setDrawDataPoints(true);
        graph.addSeries(series);
        series = new LineGraphSeries<DataPoint>(line2);
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(N_points);

        staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);




        //// PM 10
        graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("NO2 (compared with WHO 1h mean)");

        line1 = new DataPoint[N_points];
        line2 = new DataPoint[N_points];

        for (int i=0; i<N_points; i++) {
            PersonalPollutionDataPoint dataPoint = dataPoints.get(i);
            line1[i] = new DataPoint(i+1, dataPoint.getNo2());
            line2[i] = new DataPoint(i+1, who_no2_1hMean);
        };

        series = new LineGraphSeries<DataPoint>(line1);
        series.setDrawDataPoints(true);
        graph.addSeries(series);
        series = new LineGraphSeries<DataPoint>(line2);
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(N_points);

        staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


    }
}
