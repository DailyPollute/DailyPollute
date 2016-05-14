package com.github.dailypollute;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Stores locationwise pollution data
 */
public class PersonalPollutionData {


    private List<PersonalPollutionDataPoint> dataPoints = new ArrayList<PersonalPollutionDataPoint>();

    public void addPoint(double latitude, double longitude, Double no2, Double o3, Double pm10, Double pm2_5, Double temperature, Date date) {
        PersonalPollutionDataPoint newPoint = new PersonalPollutionDataPoint();
        newPoint.setLatitude(latitude);
        newPoint.setLongitude(longitude);
        newPoint.setNo2(no2);
        newPoint.setO3(o3);
        newPoint.setPm10(pm10);
        newPoint.setPm2_5(pm2_5);
        newPoint.setTemperature(temperature);
        newPoint.setDate(date);

        this.dataPoints.add(newPoint);

        //TODO: if point collection contains more than one year than delete the oldest ones
    }

    public List<PersonalPollutionDataPoint> getPoints() {
        return this.dataPoints;
    }

}
