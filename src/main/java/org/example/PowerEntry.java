package org.example;

import org.json.JSONObject;

import java.util.ArrayList;

public class PowerEntry {

    private String minutes1UTC;
    private Double solarPower;

    private boolean isAverage;

    public PowerEntry(JSONObject websiteJson){
        isAverage = false;

        minutes1UTC = websiteJson.getString("Minutes1UTC");
        solarPower = websiteJson.getDouble("SolarPower");
    }

    //Creates average
    public PowerEntry(ArrayList<PowerEntry> entries){
        isAverage = true;

        minutes1UTC = entries.get(0).minutes1UTC;
        for (PowerEntry entry : entries) {
            //Timestamp is in ISO format, lexical ordering=time ordering.
            if (entry.minutes1UTC.compareTo(minutes1UTC)>0) {
                minutes1UTC = entry.minutes1UTC;
            }
        }
        solarPower = 0.0;
        for ( PowerEntry entry : entries){
            solarPower = solarPower + entry.solarPower;
        }
        solarPower = solarPower/entries.size();

    }

}
