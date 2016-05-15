package com.github.dailypollute;

import android.os.AsyncTask;

import java.util.Date;


public class FetchData extends AsyncTask<String, Void, String> {

    private Exception exception;
    private PersonalPollutionData pollutionData;

    public FetchData(final PersonalPollutionData pollutionData) {
        this.pollutionData = pollutionData;
    }
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
}
