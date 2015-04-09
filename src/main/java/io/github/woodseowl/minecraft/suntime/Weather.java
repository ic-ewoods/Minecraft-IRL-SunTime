package io.github.woodseowl.minecraft.suntime;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

public class Weather {

    private final static String WUNDERGROUND_API_KEY = "19d0c8dd2e94cb84";
    private final TimeZone timeZone;
    private final String location;
    private CurrentObservation currentObservation = null;
    private Calendar observationExpirationTime;

    public Weather() {
        timeZone = TimeZone.getTimeZone("America/New_York");
        location = "NY/Ithaca";
    }

    public String getLocation() {
        return location;
    }

    public String getCurrentConditions() {
        try {
            retrieveCurrentObservation();
            return currentObservation.toString();
        } catch (Exception e) {
            return "Could not be retrieved [" + e.getMessage() + "]";
        }
    }

    public String getWeather() {
        try {
            retrieveCurrentObservation();
            return currentObservation.getWeather();
        } catch (Exception e) {
            return "Could not be retrieved [" + e.getMessage() + "]";
        }
    }

    public Calendar getObservationExpirationTime() {
        return observationExpirationTime;
    }

    private void retrieveCurrentObservation() throws UnirestException {
        if (currentObservation == null || currentObservation.getLastObservationTime().after(observationExpirationTime)) {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://api.wunderground.com/api/{key}/{method}/q/{id}.json")
                    .routeParam("key", WUNDERGROUND_API_KEY)
                    .routeParam("method", "conditions")
                    .routeParam("id", location)
                    .header("Accept", "application/json")
                    .asJson();
            JSONObject current_observation = (JSONObject) jsonResponse.getBody().getObject().get("current_observation");
            currentObservation = new CurrentObservation(current_observation, timeZone);
            observationExpirationTime = Calendar.getInstance(timeZone);
            observationExpirationTime.add(Calendar.HOUR, 1);
        }
    }
}