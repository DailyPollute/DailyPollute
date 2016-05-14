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
        double pollution_hour = (60./sampling_time-1)/2.;
        double pollution_year = (60./sampling_time*24*365-1)/2.;

        int N_points = 365*24*60/sampling_time-1; //At least that to

        for (int i=N_points; i>=0; i--){
            PersonalPollutionDataPoint dataPoint = new PersonalPollutionDataPoint();

            // Specify time "of request"
            Date date = new Date(date_now.getTime() - TimeUnit.MINUTES.toMillis(i*6));

            // Feed data point with fetched data
            dataPoint.setNo2((double) i);
            dataPoint.setDate(date);

            // Add point to personal list
            dataPoints.add(dataPoint);
        }

        StatisticalAnalysis statisticalAnalysis = new StatisticalAnalysis();
        statisticalAnalysis.setDataPoints(dataPoints);
        statisticalAnalysis.analyse();

        assertEquals(pollution_hour, statisticalAnalysis.getNo2_1h_mean(), 1e-8);
        assertEquals(pollution_year, statisticalAnalysis.getNo2_annual_mean(), 1e-8);
    }

}