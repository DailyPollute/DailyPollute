package com.github.dailypollute;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mebner on 15/05/16.
 */
public class FetchData extends AsyncTask<String, Void, String> {

    private Exception exception;
    private PersonalPollutionData pollutionData;

    protected String doInBackground(String... urls) {

            double latitude = -0.13;
            double longitude = 51.51;
            Date date = new Date();
            Double n02 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.N02, latitude, longitude);
            Double o3 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.O3, latitude, longitude);
            Double pm2_5 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.PM2_5, latitude, longitude);
            Double pm10 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.PM10, latitude, longitude);
            pollutionData.addPoint(latitude, longitude, n02, o3, pm10, pm2_5, null, date);
            return "";
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

    public FetchData(final PersonalPollutionData pollutionData) {
        this.pollutionData = pollutionData;
    }

    static public List<PersonalPollutionDataPoint> readData(){

        List<PersonalPollutionDataPoint> dataPoints = new ArrayList<PersonalPollutionDataPoint>();
        Date date_now = new Date();
        date_now = new Date(date_now.getTime() - TimeUnit.HOURS.toMillis(5));

        int sampling_time = 5; // in min, i.e. sampling every 6 minutes
        int i = 0;
        
        //*** new point
        PersonalPollutionDataPoint dataPoint = new PersonalPollutionDataPoint();

        // Specify time "of request"
        Date date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));

        // Feed data point with fetched data
        dataPoint.setLongitude(-0.142536);
        dataPoint.setLatitude(51.539024);
        dataPoint.setPm2_5(45.361473);
        dataPoint.setPm10(41.316109);
        dataPoint.setNo2(150.578522);
        dataPoint.setDate(date);

        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=1 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();

        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));

        // Feed data point with fetched data
        dataPoint.setLongitude(-0.141451);
        dataPoint.setLatitude(51.539135);
        dataPoint.setPm2_5(40.012085);
        dataPoint.setPm10(35.76086);
        dataPoint.setNo2(135.907242);
        dataPoint.setDate(date);

        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=2 ==============================================================================
        
        //*** new point
        dataPoint = new PersonalPollutionDataPoint();

        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));

        // Feed data point with fetched data
        dataPoint.setLongitude(-0.137352);
        dataPoint.setLatitude(51.534724);
        dataPoint.setPm2_5(41.676838);
        dataPoint.setPm10(35.437416);
        dataPoint.setNo2(131.939392);
        dataPoint.setDate(date);

        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=3 ==============================================================================
        
        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.138416);
        dataPoint.setLatitude(51.534462);
        dataPoint.setPm2_5(39.628906);
        dataPoint.setPm10(38.779591);
        dataPoint.setNo2(161.927353);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=4 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.117283);
        dataPoint.setLatitude(51.513221);
        dataPoint.setPm2_5(52.072773);
        dataPoint.setPm10(57.063942);
        dataPoint.setNo2(307.804047);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=5 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.115295);
        dataPoint.setLatitude(51.513109);
        dataPoint.setPm2_5(62.980583);
        dataPoint.setPm10(51.285519);
        dataPoint.setNo2(266.642639);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=6 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.115311);
        dataPoint.setLatitude(51.512596);
        dataPoint.setPm2_5(45.646408);
        dataPoint.setPm10(57.597893);
        dataPoint.setNo2(274.856873);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=7 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.115751);
        dataPoint.setLatitude(51.512449);
        dataPoint.setPm2_5(47.313629);
        dataPoint.setPm10(47.164623);
        dataPoint.setNo2(216.783554);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=8 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.114968);
        dataPoint.setLatitude(51.511027);
        dataPoint.setPm2_5(43.086388);
        dataPoint.setPm10(36.018337);
        dataPoint.setNo2(123.922249);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=9 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.115322);
        dataPoint.setLatitude(51.510947);
        dataPoint.setPm2_5(49.065163);
        dataPoint.setPm10(39.512665);
        dataPoint.setNo2(135.342026);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=10 ==============================================================================

        //*** new point
        dataPoint = new PersonalPollutionDataPoint();
        
        // Specify time "of request"
        date = new Date(date_now.getTime() + TimeUnit.MINUTES.toMillis(i*sampling_time));
        
        // Feed data point with fetched data
        dataPoint.setLongitude(-0.115751);
        dataPoint.setLatitude(51.511561);
        dataPoint.setPm2_5(41.207497);
        dataPoint.setPm10(28.75528);
        dataPoint.setNo2(91.448433);
        dataPoint.setDate(date);
        
        // Add point to personal list
        dataPoints.add(dataPoint);
        i++;
        // i=11 ==============================================================================

        return dataPoints;
    }
}
