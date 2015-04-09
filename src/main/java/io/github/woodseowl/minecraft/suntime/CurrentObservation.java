package io.github.woodseowl.minecraft.suntime;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class CurrentObservation {
    private final Calendar lastObservation;
    private final String weather;
    private final String temp_f;

    public CurrentObservation(JSONObject currentObservation, TimeZone timeZone) {
        int observation_epoch = Integer.parseInt(currentObservation.get("observation_epoch").toString());
        lastObservation = Calendar.getInstance(timeZone);
        lastObservation.setTimeInMillis(observation_epoch * 1000L);

        weather = currentObservation.get("weather").toString();
        temp_f = currentObservation.get("temp_f").toString();
    }

    public String toString() {
        return getWeather() + ", " + getTemp() + " deg F as of " + getLastObservation();
    }

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp_f;
    }

    public String getLastObservation() {
        SimpleDateFormat format = new SimpleDateFormat("h:mm aa zzz");
        return format.format(lastObservation.getTime());
    }

    public Calendar getLastObservationTime() {
        return lastObservation;
    }
}
