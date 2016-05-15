package com.github.dailypollute;

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
    private double who_pm2_5_annualMean = 10;  // ug per m3
    private double who_pm2_5_24hMean = 25;     // ug per m3

    private double who_pm10_annualMean = 20;   // ug per m3
    private double who_pm10_24hMean = 50;      // ug per m3

    private double who_o3_8hMean = 100;        // ug per m3

    private double who_no2_annualMean = 40;    // ug per m3
    private double who_no2_1hMean = 200;       // ug per m3

    // Computed values in data
    private double pm2_5_annualMean = 0;
    private double pm2_5_24hMean = 0;   

    private double pm10_annualMean = 0; 
    private double pm10_24hMean = 0;    

    private double o3_8hMean = 0;      

    private double no2_annualMean = 0;
    private double no2_1hMean = 0;

    // Update frequency of PersonalPollutionDataPoint
    private double updateTime = 15;         // minutes

    //
    public void setDataPoints(List<PersonalPollutionDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    // Analyse data points w.r.t. to WHO recommended figures
    public void analyse() {

        computeRelevantPm2_5Values();
        computeRelevantPm10Values();
        computeRelevantO3Values();
        computeRelevantNo2Values();

        System.out.println("PM2.5-levels are: (recommended)");
        System.out.println("\tannual mean: " + pm2_5_annualMean + " (" + who_pm2_5_annualMean + ")");
        System.out.println("\t24h mean:     " + pm2_5_24hMean + " (" + who_pm2_5_24hMean + ")");

        System.out.println("PM10-levels are: (recommended)");
        System.out.println("\tannual mean: " + pm10_annualMean + " (" + who_pm10_annualMean + ")");
        System.out.println("\t24h mean:     " + pm10_24hMean + " (" + who_pm10_24hMean + ")");

        System.out.println("O3-levels are: (recommended)");
        System.out.println("\t8h mean:     " + o3_8hMean + " (" + who_o3_8hMean + ")");        
        
        System.out.println("NO2-levels are: (recommended)");
        System.out.println("\tannual mean: " + no2_annualMean + " (" + who_no2_annualMean + ")");
        System.out.println("\t1h mean:     " + no2_1hMean + " (" + who_no2_1hMean + ")");

    }

    public void showAnalysis() {

    }



    // Get relevant PM2.5 amounts based on the WHO guideline levels
    private void computeRelevantPm2_5Values(){

        int pm2_5_annualMean_pointnumber = 0;
        int pm2_5_24hMean_pointnumber = 0;

        // Use this helper to compensate for values in case they are not given
        double pm2_5_latest_value = 0;

        Date dateNow = new Date();
        Date dateOneYearBefore = new Date(dateNow.getTime() - TimeUnit.DAYS.toMillis(365)); //subtracts one year
        Date dateOneDayBefore = new Date(dateNow.getTime() - TimeUnit.HOURS.toMillis(24));  //subtracts 24 hours

        if (dataPoints == null) {
            throw new RuntimeException("Null dataPoints");
        }

        // Iterate over all data points to compute WHO recommended values for comparison
        for (PersonalPollutionDataPoint point : dataPoints) {
            // Save latest value in case future point will not be given (i.e. use last information in case no value available)
            if (point.havePm2_5()) {
                pm2_5_latest_value = point.getPm2_5();
            }
            Date date = point.getDate();

            // Annual average
            if (date.after(dateOneYearBefore)){
                pm2_5_annualMean += pm2_5_latest_value;
                pm2_5_annualMean_pointnumber += 1;

                // One hour average
                if (date.after(dateOneDayBefore)){
                    pm2_5_24hMean += pm2_5_latest_value;
                    pm2_5_24hMean_pointnumber += 1;
                }
            }
        }

        // Normalize to get annual and one hour average
        pm2_5_annualMean /=  (double) pm2_5_annualMean_pointnumber;
        pm2_5_24hMean /= (double) pm2_5_24hMean_pointnumber;
    }


    // Get relevant PM10 amounts based on the WHO guideline levels
    private void computeRelevantPm10Values(){

        int pm10_annualMean_pointnumber = 0;
        int pm10_24hMean_pointnumber = 0;

        // Use this helper to compensate for values in case they are not given
        double pm10_latest_value = 0;

        Date dateNow = new Date();
        Date dateOneYearBefore = new Date(dateNow.getTime() - TimeUnit.DAYS.toMillis(365)); //subtracts one year
        Date dateOneDayBefore = new Date(dateNow.getTime() - TimeUnit.HOURS.toMillis(24));  //subtracts 24 hours

        if (dataPoints == null) {
            throw new RuntimeException("Null dataPoints");
        }

        // Iterate over all data points to compute WHO recommended values for comparison
        for (PersonalPollutionDataPoint point : dataPoints) {
            // Save latest value in case future point will not be given (i.e. use last information in case no value available)
            if (point.havePm10()) {
                pm10_latest_value = point.getPm10();
            }
            Date date = point.getDate();

            // Annual average
            if (date.after(dateOneYearBefore)){
                pm10_annualMean += pm10_latest_value;
                pm10_annualMean_pointnumber += 1;

                // One hour average
                if (date.after(dateOneDayBefore)){
                    pm10_24hMean += pm10_latest_value;
                    pm10_24hMean_pointnumber += 1;
                }
            }
        }

        // Normalize to get annual and one hour average
        pm10_annualMean /=  (double) pm10_annualMean_pointnumber;
        pm10_24hMean /= (double) pm10_24hMean_pointnumber;
    }


    // Get relevant O3 amounts based on the WHO guideline levels
    private void computeRelevantO3Values(){

        int o3_8hMean_pointnumber = 0;

        // Use this helper to compensate for values in case they are not given
        double o3_latest_value = 0;

        Date dateNow = new Date();
        Date dateEightHoursBefore = new Date(dateNow.getTime() - TimeUnit.HOURS.toMillis(8));  //subtracts one hour

        if (dataPoints == null) {
            throw new RuntimeException("Null dataPoints");
        }

        // Iterate over all data points to compute WHO recommended values for comparison
        for (PersonalPollutionDataPoint point : dataPoints) {
            // Save latest value in case future point will not be given (i.e. use last information in case no value available)
            if (point.haveO3()) {
                o3_latest_value = point.getO3();
            }
            Date date = point.getDate();


            // Eight hour average
            if (date.after(dateEightHoursBefore)){
                o3_8hMean += o3_latest_value;
                o3_8hMean_pointnumber += 1;
            }
        }

        // Normalize to get annual and one hour average
        o3_8hMean /= (double) o3_8hMean_pointnumber;
    }


    // Get relevant NO2 amounts based on the WHO guideline levels
    private void computeRelevantNo2Values(){

        int no2_annualMean_pointnumber = 0;
        int no2_1hMean_pointnumber = 0;

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
                no2_annualMean += no2_latest_value;
                no2_annualMean_pointnumber += 1;

                // One hour average
                if (date.after(dateOneHourBefore)){
                    no2_1hMean += no2_latest_value;
                    no2_1hMean_pointnumber += 1;
                }
            }
        }

        // Normalize to get annual and one hour average
        no2_annualMean /=  (double) no2_annualMean_pointnumber;
        no2_1hMean /= (double) no2_1hMean_pointnumber;
    }


    public double getNo2_annualMean() {
        return no2_annualMean;
    }

    public double getNo2_1hMean() {
        return no2_1hMean;
    }

    public double getO3_8hMean() {
        return o3_8hMean;
    }

    public double getPm10_24hMean() {
        return pm10_24hMean;
    }

    public double getPm10_annualMean() {
        return pm10_annualMean;
    }

    public double getPm2_5_24hMean() {
        return pm2_5_24hMean;
    }

    public double getPm2_5_annualMean() {
        return pm2_5_annualMean;
    }

    public double getRelativePm2_5_24hMean() {
        return pm2_5_24hMean / (double) who_pm2_5_24hMean;
    }

    public double getRelativePm2_5_annualMean() {
        return pm2_5_annualMean / (double) who_pm2_5_annualMean;
    }

    public double getRelativePm10_24hMean() {
        return pm10_24hMean / (double) who_pm10_24hMean;
    }

    public double getRelativePm10_annualMean() {
        return pm10_annualMean / (double) who_pm10_annualMean;
    }

    public double getRelativeNo2_1hMean() {
        return no2_1hMean / (double) who_no2_1hMean;
    }

    public double getRelativeNo2_annualMean() {
        return no2_annualMean / (double) who_no2_annualMean;
    }

    public double getRelativeO3_8hMean() {
            return o3_8hMean / (double) who_o3_8hMean;
    }

}
