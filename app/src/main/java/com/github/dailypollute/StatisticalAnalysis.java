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

    // WHO guideline values (see http://www.who.int/mediacentre/factsheets/fs313/en/)
    private double pm2_5_annual_mean = 10;  // ug per m3
    private double pm2_5_24h_mean = 25;     // ug per m3

    private double pm10_annual_mean = 20;   // ug per m3
    private double pm10_24h_mean = 50;      // ug per m3

    private double o3_8h_mean = 100;        // ug per m3

    private double no2_annual_mean = 40;    // ug per m3
    private double no2_1h_mean = 200;       // ug per m3

    // Update frequency of PersonalPollutionDataPoint
    private double updateTime = 15;         // minutes

    //
    public void setDataPoints(List<PersonalPollutionDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    //
    public void analyse() {
        
        ArrayList<Double> no2_values = getRelevantNo2Values();
        
        System.out.println("NO2-levels are:");
        System.out.println("\tannual mean: " + no2_values.get(0));
        System.out.println("\t1h mean:     " + no2_values.get(1));
    }

    // Get relevant NO2 amounts based on the WHO guideline levels
    private ArrayList<Double> getRelevantNo2Values(){

        ArrayList<Double> no2_values = new ArrayList<Double>();

        double no2_annual_mean = 0;
        double no2_1h_mean = 0;

        int no2_annual_mean_pointnumber = 0;
        int no2_1h_mean_pointnumber = 0;

        // Use this helper to compensate for values in case they are not given
        double no2_latest_value = 0;

        Date dateNow = new Date();
        Date dateOneYearBefore = new Date(dateNow.getTime() - TimeUnit.DAYS.toMillis(365)); //subtracts one year
        Date dateOneHourBefore = new Date(dateNow.getTime() - TimeUnit.HOURS.toMillis(1));  //subtracts one hour

//        System.out.println("Now: " + dateNow);

        if (dataPoints == null) {
            throw new RuntimeException("Null dataPoints");
        }


        for (PersonalPollutionDataPoint point : dataPoints) {
            // Save latest value in case one point for interpolation is not given
            if (point.haveNo2()) {
                no2_latest_value = point.getNo2();
            }

            Date date = point.getDate();

            if (date.after(dateOneYearBefore)){
                no2_annual_mean += no2_latest_value;
                no2_annual_mean_pointnumber += 1;

                if (date.after(dateOneHourBefore)){
                    System.out.println(point.getDate() + ": " + point.getNo2());

                    no2_1h_mean += no2_latest_value;
                    no2_1h_mean_pointnumber += 1;
                }

            }
        }

        no2_annual_mean /= no2_annual_mean_pointnumber;
        no2_1h_mean /= no2_1h_mean_pointnumber;

        no2_values.add(no2_annual_mean);
        no2_values.add(no2_1h_mean);

        return no2_values;

    }
}
