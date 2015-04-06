package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeHelpers {
    public static final int DAYLENGTH = 24000;
    public static final int SUNRISE = 0;
    public static final int SUNSET = 12000;

    Calendar realDate;
    private World world;

    public TimeHelpers(World world) {
        this.world = world;
        realDate = Calendar.getInstance();
    }

    public TimeHelpers(World world, Calendar realDate) {
        this.world = world;
        this.realDate = realDate;
    }

    public final String getRealTimeString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm z");
        return dateFormat.format(realDate.getTime());
    }

    public final Long getRealTimeLong() {
        int hour = realDate.get(Calendar.HOUR_OF_DAY) * 1000;
        Double minute = realDate.get(Calendar.MINUTE) * 1000 / 60.0;
        return Math.round(hour + minute);
    }

    public final Long convertRealTimeToWorldTime(Long realTime) {
        double realSunrise = 6000;
        double realSunset = 18000;

        if (realTime > realSunrise && realTime < realSunset) {
            double dayFraction = (realTime - realSunrise) / (realSunset - realSunrise);
            return Math.round((SUNSET - SUNRISE) * dayFraction) + SUNRISE;
        } else {
            double nightFraction;
            if (realTime <= realSunrise) {
                nightFraction = (DAYLENGTH + realTime - realSunset) / (DAYLENGTH + realSunrise - realSunset);
            } else {
                nightFraction = (realTime - realSunset) / (DAYLENGTH + realSunrise - realSunset);
            }
            return Math.round((DAYLENGTH - SUNSET) * nightFraction) + SUNSET;
        }
    }

    public final Double getWorldTime() {
        return world.getTime() / 1000.0;
    }

    public final Double getWorldFullTime() {
        return world.getFullTime() / 1000.0;
    }
}
