package io.github.woodseowl.minecraft.suntime;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

public class Weather {

    private final static String WUNDERGROUND_API_KEY = "19d0c8dd2e94cb84";

    public CurrentObservation getCurrentObservation() throws Exception {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://api.wunderground.com/api/{key}/{method}/q/{id}.json")
                .routeParam("key", WUNDERGROUND_API_KEY)
                .routeParam("method", "conditions")
                .routeParam("id", "NY/Ithaca")
                .header("Accept", "application/json")
                .asJson();

        JSONObject currentObservation = (JSONObject)jsonResponse.getBody().getObject().get("current_observation");

        return new CurrentObservation(currentObservation);
    }
}