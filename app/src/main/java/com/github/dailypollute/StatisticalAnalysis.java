package com.github.dailypollute;

import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mebner on 14/05/16.
 */
public class StatisticalAnalysis {

    private List<PersonalPollutionDataPoint> dataPoints = null;

    // Min and max values for pollution levels
    private double no2_min = 0;
    private double no2_max = 10;

    private double o3_min = 0;
    private double o3_max = 10;

    private double pm2_5_min = 0;
    private double pm2_5_max = 10;

    private double pm10_min = 0;
    private double pm10_max = 10;

    // WHO recommended guideline values (see http://www.who.int/mediacentre/factsheets/fs313/en/)
    private double who_pm2_5_annual_mean = 10;  // ug per m3
    private double who_pm2_5_24h_mean = 25;     // ug per m3

    private double who_pm10_annual_mean = 20;   // ug per m3
    private double who_pm10_24h_mean = 50;      // ug per m3

    private double who_o3_8h_mean = 100;        // ug per m3

    private double who_no2_annual_mean = 40;    // ug per m3
    private double who_no2_1h_mean = 200;       // ug per m3

    // Computed values in data
    private double no2_annual_mean = 0;
    private double no2_1h_mean = 0;

    // Update frequency of PersonalPollutionDataPoint
    private double updateTime = 15;         // minutes

    //
    public void setDataPoints(List<PersonalPollutionDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    //
    public void analyse() {
        
        computeRelevantNo2Values();
        
        System.out.println("NO2-levels are: (recommended)");
        System.out.println("\tannual mean: " + no2_annual_mean + " (" + who_no2_annual_mean + ")");
        System.out.println("\t1h mean:     " + no2_1h_mean + " (" + who_no2_1h_mean + ")");
    }

    // Get relevant NO2 amounts based on the WHO guideline levels
    private void computeRelevantNo2Values(){

        int no2_annual_mean_pointnumber = 0;
        int no2_1h_mean_pointnumber = 0;

        // Use this helper to compensate for values in case they are not given
        double no2_latest_value = 0;

        Date dateNow = new Date();
        Date dateOneYearBefore = new Date(dateNow.getTime() - TimeUnit.DAYS.toMillis(365)); //subtracts one year
        Date dateOneHourBefore = new Date(dateNow.getTime() - TimeUnit.HOURS.toMillis(1));  //subtracts one hour

        if (dataPoints == null) {
            throw new RuntimeException("Null dataPoints");
        }

        // Iterate over all data points to compute WHO recommended values for comparison
        for (PersonalPollutionDataPoint point : dataPoints) {
            // Save latest value in case future point will not be given (i.e. use last information in case no value available)
            if (point.haveNo2()) {
                no2_latest_value = point.getNo2();
            }

            Date date = point.getDate();

            // Annual average
            if (date.after(dateOneYearBefore)){
                no2_annual_mean += no2_latest_value;
                no2_annual_mean_pointnumber += 1;

                // One hour average
                if (date.after(dateOneHourBefore)){
                    no2_1h_mean += no2_latest_value;
                    no2_1h_mean_pointnumber += 1;
                }
            }
        }

        // Normalize to get annual and one hour average
        no2_annual_mean /=  (double) no2_annual_mean_pointnumber;
        no2_1h_mean /= (double) no2_1h_mean_pointnumber;
    }

    public double getNo2_annual_mean() {
        return no2_annual_mean;
    }

    public double getNo2_1h_mean() {
        return no2_1h_mean;
    }
}
