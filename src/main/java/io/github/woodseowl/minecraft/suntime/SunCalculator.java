package io.github.woodseowl.minecraft.suntime;

//import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
//import com.luckycatlabs.sunrisesunset.dto.Location;

import java.util.Calendar;

public class SunCalculator {

//    private SunriseSunsetCalculator calculator;

    public SunCalculator() {
//        Location location = new Location("42", "-76");
//        calculator = new SunriseSunsetCalculator(location, "America/New_York");
    }

    public Calendar getSunrise() {
        Calendar sunrise = Calendar.getInstance();
        sunrise.set(Calendar.HOUR, 5);
        sunrise.set(Calendar.MINUTE, 30);
        return sunrise;
        // return calculator.getOfficialSunriseCalendarForDate(Calendar.getInstance());
    }

    public Calendar getSunset() {
        Calendar sunset = Calendar.getInstance();
        sunset.set(Calendar.HOUR, 18);
        sunset.set(Calendar.MINUTE, 30);
        return sunset;
        // return calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance());
    }
}
