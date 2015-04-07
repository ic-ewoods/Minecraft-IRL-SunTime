package io.github.woodseowl.minecraft.suntime;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.util.Calendar;

public class SunCalculator {

    private SunriseSunsetCalculator calculator;

    public SunCalculator() {
        Location location = new Location("42", "-76");
        calculator = new SunriseSunsetCalculator(location, "America/New_York");
    }

    public Calendar getSunrise() {
        return calculator.getOfficialSunriseCalendarForDate(Calendar.getInstance());
    }

    public Calendar getSunset() {
        return calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance());
    }
}
