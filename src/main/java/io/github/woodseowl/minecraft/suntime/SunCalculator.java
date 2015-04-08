package io.github.woodseowl.minecraft.suntime;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.util.Calendar;
import java.util.TimeZone;

public class SunCalculator {

    private final TimeZone timeZone;
    private SunriseSunsetCalculator calculator;

    public SunCalculator(String latitude, String longitude, TimeZone timeZone) {
        this.timeZone = timeZone;
        calculator = new SunriseSunsetCalculator(new Location(latitude, longitude), timeZone);
    }

    public Calendar getSunrise() {
        return calculator.getOfficialSunriseCalendarForDate(Calendar.getInstance(timeZone));
    }

    public Calendar getSunset() {
        return calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance(timeZone));
    }
}
