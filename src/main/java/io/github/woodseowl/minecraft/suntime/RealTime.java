package io.github.woodseowl.minecraft.suntime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class RealTime {

    private final Calendar realTime;
    private final Calendar sunrise;
    private final Calendar sunset;


    public RealTime(SunCalculator sunCalculator) {
        this(Calendar.getInstance(), sunCalculator.getSunrise(), sunCalculator.getSunset());
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

    public String getMinecraftFormatTime() {
        int hour = realTime.get(Calendar.HOUR_OF_DAY);
        double minute = realTime.get(Calendar.MINUTE) / 60.0;
        Long minecraftTime = Math.round((hour + minute) * 1000);
        return minecraftTime.toString();
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
