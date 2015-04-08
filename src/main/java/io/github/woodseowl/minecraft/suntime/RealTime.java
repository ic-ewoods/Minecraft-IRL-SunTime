package io.github.woodseowl.minecraft.suntime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class RealTime {

    private final Calendar realTime;
    private final Calendar sunrise;
    private final Calendar sunset;


    public RealTime(SunCalculator sunCalculator, TimeZone timeZone) {
        this(Calendar.getInstance(timeZone), sunCalculator.getSunrise(), sunCalculator.getSunset());
    }

    public RealTime(Calendar realTime, Calendar sunrise, Calendar sunset) {
        this.realTime = realTime;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(realTime.getTime());
    }

    public boolean isDay() {
        return realTime.after(sunrise) && realTime.before(sunset);
    }

    public double getDayFraction() {
        if (isDay()) {
            long timeSinceSunrise = realTime.getTimeInMillis() - sunrise.getTimeInMillis();
            return timeSinceSunrise / (getLengthOfDayInMillis() * 1.0);
        }
        return 0.0;
    }

    public double getNightFraction() {
        if (realTime.after(sunset)) {
            long timeSinceSunset = realTime.getTimeInMillis() - sunset.getTimeInMillis();
            return timeSinceSunset / (getLengthOfNightInMillis() * 1.0);
        }
        if (realTime.before(sunrise)) {
            long timeBeforeSunrise = sunrise.getTimeInMillis() - realTime.getTimeInMillis();
            return (getLengthOfNightInMillis() - timeBeforeSunrise) / (getLengthOfNightInMillis() * 1.0);
        }
        return 0.0;
    }

    private long getLengthOfDayInMillis() {
        return sunset.getTimeInMillis() - sunrise.getTimeInMillis();
    }

    private long getLengthOfNightInMillis() {
        return TimeUnit.DAYS.toMillis(1) - getLengthOfDayInMillis();
    }

    public String getSunriseTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(sunrise.getTime());
    }

    public String getSunsetTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(sunset.getTime());
    }
}
