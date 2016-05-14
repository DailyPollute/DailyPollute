package com.github.dailypollute;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void myFirstTest() throws Exception {

        List<PersonalPollutionDataPoint> dataPoints = new ArrayList<PersonalPollutionDataPoint>();
        Date date_now = new Date();

        int sampling_time = 6; // in min, i.e. sampling every 6 minutes
        double pollution_hour = (double) 60/sampling_time*(60/sampling_time - 1)/2/(60/sampling_time);
        double pollution_year = (double) 60/sampling_time*365*(60/sampling_time - 1)/2 * 24 * 365;

//        int N_points = 365*60*60/sampling_time;
        int N_points = 15;

        for (int i=N_points; i>=0; i--){
            PersonalPollutionDataPoint dataPoint = new PersonalPollutionDataPoint();

            // Specify time "of request"
            Date date = new Date(date_now.getTime() - TimeUnit.MINUTES.toMillis(i*6));

            // Feed data point with fetched data
            dataPoint.setNo2((double) i);
            dataPoint.setDate(date);

            // Add point to personal list
            dataPoints.add(dataPoint);

            System.out.println(dataPoints.get(N_points-i).getDate() + ": " + dataPoints.get(N_points-i).getNo2());
        }
        System.out.println("\n");
        System.out.println("pollution hour: " + pollution_hour);
        System.out.println("pollution year: " + pollution_year);

        StatisticalAnalysis statisticalAnalysis = new StatisticalAnalysis();
        statisticalAnalysis.setDataPoints(dataPoints);
        statisticalAnalysis.analyse();

        System.out.println("NO2-levels are:");
        System.out.println(date_now);
        assertEquals(4, 2 + 2);
    }

}