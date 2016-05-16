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
    public void computeRelevantWhoValues() throws Exception {

        PersonalPollutionData data = new PersonalPollutionData();

        List<PersonalPollutionDataPoint> dataPoints = new ArrayList<PersonalPollutionDataPoint>();
        Date date_now = new Date();

        int sampling_time = 6; // in min, i.e. sampling every 6 minutes
        
        double pollution_pm2_5_24hMean = (60./sampling_time*24-1)/2.;
        double pollution_pm2_5_annualMean = (60./sampling_time*24*365-1)/2.;

        double pollution_pm10_24hMean = (60./sampling_time*24-1)/2.;
        double pollution_pm10_annualMean = (60./sampling_time*24*365-1)/2.;

        double pollution_no2_1hMean = (60./sampling_time*1-1)/2.;
        double pollution_no2_annualMean = (60./sampling_time*24*365-1)/2.;
        
        double pollution_o3_8hMean = (60./sampling_time*8-1)/2.;

        int N_points = 365*24*60/sampling_time-1; //At least that number in order to have sampled one entire year

        for (int i=N_points; i>=0; i--){
            PersonalPollutionDataPoint dataPoint = new PersonalPollutionDataPoint();

            // Specify time "of request"
            Date date = new Date(date_now.getTime() - TimeUnit.MINUTES.toMillis(i*sampling_time));

            // Feed data point with fetched data
            dataPoint.setO3((double) i);
            dataPoint.setNo2((double) i);
            dataPoint.setPm2_5((double) i);
            dataPoint.setPm10((double) i);
            dataPoint.setDate(date);

            // Add point to personal list
            dataPoints.add(dataPoint);
        }

        StatisticalAnalysis statisticalAnalysis = new StatisticalAnalysis();
        statisticalAnalysis.setDataPoints(dataPoints);
        statisticalAnalysis.analyse();

        assertEquals(pollution_pm2_5_24hMean, statisticalAnalysis.getPm2_5_24hMean(), 1e-8);
        assertEquals(pollution_pm2_5_annualMean, statisticalAnalysis.getPm2_5_annualMean(), 1e-8);

        assertEquals(pollution_pm10_24hMean, statisticalAnalysis.getPm10_24hMean(), 1e-8);
        assertEquals(pollution_pm10_annualMean, statisticalAnalysis.getPm10_annualMean(), 1e-8);

        assertEquals(pollution_no2_1hMean, statisticalAnalysis.getNo2_1hMean(), 1e-8);
        assertEquals(pollution_no2_annualMean, statisticalAnalysis.getNo2_annualMean(), 1e-8);

        assertEquals(pollution_o3_8hMean, statisticalAnalysis.getO3_8hMean(), 1e-8);
    }
}