package com.github.dailypollute;

import java.util.Date;

/**
 * Stores pollution for a particular location
 */
public class PersonalPollutionDataPoint {
    private double latitude = 0;
    private double longitude = 0;

    private Double no2 = null;
    private Double o3 = null;
    private Double pm10 = null;
    private Double pm2_5 = null;

    private Double temperature = null;

    private Date date = null;

    public boolean haveNo2() {
        return (no2 != null);
    }

    public boolean haveO3() {
        return (o3 != null);
    }

    public boolean havePm10() {
        return (pm10 != null);
    }

    public boolean havePm2_5() {
        return (pm2_5 != null);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getO3() {
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(Double pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
