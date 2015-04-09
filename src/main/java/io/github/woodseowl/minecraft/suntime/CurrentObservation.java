package io.github.woodseowl.minecraft.suntime;

import org.json.JSONObject;

public class CurrentObservation {
    private String weather;
    private String temp_f;

    public CurrentObservation(JSONObject currentObservation) {
        weather = currentObservation.get("weather").toString();
        temp_f = currentObservation.get("temp_f").toString();
    }

    public String toString() {
        return getWeather() + ", " + getTemp() + " deg F";
    }

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp_f;
    }
}
