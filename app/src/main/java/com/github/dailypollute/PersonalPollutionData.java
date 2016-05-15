package com.github.dailypollute;

import android.app.Service;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Stores locationwise pollution data
 */
public class PersonalPollutionData implements Serializable {

    static PersonalPollutionData instance = null;

    public static PersonalPollutionData getInstance() {
        if( instance == null )
            instance = loadData();
        if( instance == null )
            instance = new PersonalPollutionData();
        return instance;
    }

    public static void saveData(PersonalPollutionData instance){
        ObjectOutput out;
        try {
            File appDir = HomePage.getAppContext().getFilesDir();
            File outFile = new File(appDir, "PersonalPollutionData.data");
            out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(instance);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PersonalPollutionData loadData(){
        ObjectInput in;
        PersonalPollutionData data = null;
        try {
            File appDir = HomePage.getAppContext().getFilesDir();
            in = new ObjectInputStream(new FileInputStream(new File(appDir, "PersonalPollutionData.data")));
            data = (PersonalPollutionData) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<PersonalPollutionDataPoint> dataPoints = new ArrayList<PersonalPollutionDataPoint>();

    public synchronized  void addPoint(double latitude, double longitude, Double no2, Double o3, Double pm10, Double pm2_5, Double temperature, Date date) {
        PersonalPollutionDataPoint newPoint = new PersonalPollutionDataPoint(latitude, longitude, no2, o3, pm10, pm2_5, temperature, date);
        newPoint.setLatitude(latitude);
        newPoint.setLongitude(longitude);
        newPoint.setNo2(no2);
        newPoint.setO3(o3);
        newPoint.setPm10(pm10);
        newPoint.setPm2_5(pm2_5);
        newPoint.setTemperature(temperature);
        newPoint.setDate(date);

        this.dataPoints.add(newPoint);
        PersonalPollutionData.saveData(this);

        //TODO: if point collection contains more than one year than delete the oldest ones
    }

    public synchronized List<PersonalPollutionDataPoint> getPoints() {
        ArrayList<PersonalPollutionDataPoint> newList = new ArrayList<PersonalPollutionDataPoint>();
        for (PersonalPollutionDataPoint point : dataPoints) {
            newList.add(new PersonalPollutionDataPoint(point));
        }
        return newList;
    }

}
