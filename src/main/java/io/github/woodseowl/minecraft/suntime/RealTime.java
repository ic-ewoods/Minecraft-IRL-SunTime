package io.github.woodseowl.minecraft.suntime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class RealTime {

    private final Calendar realTime;
    private final Calendar sunrise;
    private final Calendar sunset;


    public RealTime(SunCalculator sunCalculator) {
        realTime = Calendar.getInstance();
        sunrise = sunCalculator.getSunrise();
        sunset = sunCalculator.getSunset();
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(realTime.getTime());
    }

    public String getMinecraftFormatTime() {
        int hour = realTime.get(Calendar.HOUR_OF_DAY);
        double minute = realTime.get(Calendar.MINUTE) / 60.0;
        Long minecraftTime = Math.round((hour + minute) * 1000);
        return minecraftTime.toString();
    }

    public boolean isDay() {
        return realTime.after(sunrise) && realTime.before(sunset);
    }

    public long getDayFraction() {
        long timeSinceSunrise = realTime.getTimeInMillis() - sunrise.getTimeInMillis();
        return timeSinceSunrise / getLengthOfDayInMillis();
    }

    public long getNightFraction() {
        if (realTime.after(sunset)) {
            long timeSinceSunset = realTime.getTimeInMillis() - sunset.getTimeInMillis();
            return timeSinceSunset / getLengthOfNightInMillis();
        } else {
            long timeBeforeSunrise = sunrise.getTimeInMillis() - realTime.getTimeInMillis();
            return (getLengthOfNightInMillis() - timeBeforeSunrise) / getLengthOfNightInMillis();
        }
    }

    private long getLengthOfDayInMillis() {
        return sunset.getTimeInMillis() - sunrise.getTimeInMillis();
    }

    private long getLengthOfNightInMillis() {
        return TimeUnit.DAYS.toMillis(1) - getLengthOfDayInMillis();
    }
}
