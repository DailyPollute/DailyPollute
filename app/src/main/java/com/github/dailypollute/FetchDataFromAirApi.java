package com.github.dailypollute;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;

public class FetchDataFromAirApi {
    enum Pollutant {
        PM10, PM2_5, O3, N02
    }

    public static Double GetData(Pollutant pollutant, double latitude, double longitude) {
        RestClient client = new RestClient();
        final String latitudeString = new Double(latitude).toString();
        final String longitudeString = new Double(longitude).toString();
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("geometryType", "esriGeometryPoint"));
        params.add(new BasicNameValuePair("geometry", latitudeString + "," + longitudeString));
        params.add(new BasicNameValuePair("sr", ""));
        params.add(new BasicNameValuePair("layers", ""));
        params.add(new BasicNameValuePair("time", ""));
        params.add(new BasicNameValuePair("layerTimeOptions", ""));
        params.add(new BasicNameValuePair("tolerance", "1"));
        params.add(new BasicNameValuePair("mapExtent", "104,35.6,-94.32,41"));
        params.add(new BasicNameValuePair("imageDisplay", "600,550,96"));
        params.add(new BasicNameValuePair("returnGeometry", "false"));
        params.add(new BasicNameValuePair("maxAllowableOffset", ""));
        params.add(new BasicNameValuePair("f", "pjson"));
        String pollutantString = "";
        switch (pollutant) {
            case PM2_5:
                pollutantString = "PM25now";
                break;
            case PM10:
                pollutantString = "PM10now";
                break;
            case O3:
                pollutantString = "O3now";
                break;
            case N02:
                pollutantString = "NO2now";
                break;
        }
        final String uri = "http://webgis.erg.kcl.ac.uk/arcgis/rest/services/" + pollutantString + "/MapServer/identify";
        try {
            client.Execute(RestClient.RequestMethod.GET, uri, new ArrayList<NameValuePair>(), params);
            String response = client.response;

            try {
                JSONObject entity = new JSONObject(new JSONTokener(response));
                JSONArray entityResults = entity.getJSONArray("results");
                JSONObject nextObject = entityResults.getJSONObject(0);
                if (nextObject.has("value")) {
                    String returnString = nextObject.getString("value");
                    try {
                        return Double.parseDouble(returnString);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                } else {
                    return null;
                }
            } catch (JSONException exception) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}