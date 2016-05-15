package com.github.dailypollute;

import android.os.AsyncTask;


public class FetchData extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            double latitude = -0.13;
            double longitude = 51.51;
            Double n02 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.N02, latitude, longitude);
            Double o3 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.O3, latitude, longitude);
            Double pm2_5 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.PM2_5, latitude, longitude);
            Double pm10 = FetchDataFromAirApi.GetData(FetchDataFromAirApi.Pollutant.PM10, latitude, longitude);
            return "";
        }

        protected void onPostExecute(String feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
}
