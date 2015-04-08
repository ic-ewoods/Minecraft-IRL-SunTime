package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

import java.util.TimeZone;

public class SunTime {

    public final RealTime realTime;
    private final MinecraftTime minecraftTime;

    public SunTime(MinecraftTime minecraftTime, RealTime realTime) {
        this.minecraftTime = minecraftTime;
        this.realTime = realTime;
    }

    public static SunTime getInstance(World world) {
        TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
        SunCalculator sunCalculator = new SunCalculator("42", "-72", timeZone);
        MinecraftTime minecraftTime = new MinecraftTime(world.getTime());
        RealTime realTime = new RealTime(sunCalculator, timeZone);

        return new SunTime(minecraftTime, realTime);
    }

    public String getDayTime() {
        return minecraftTime.getDayTime();
    }

    public String getRealTime() {
        return realTime.getTime();
    }

    public String getRealDayTime() {
        return getRealDayTimeLong().toString();
    }

    public Long getRealDayTimeLong() {
        long daytime;
        if (realTime.isDay()) {
            double dayFraction = realTime.getDayFraction();
            daytime = minecraftTime.calculateDayTime(dayFraction);
        } else {
            double nightFraction = realTime.getNightFraction();
            daytime = minecraftTime.calculateNightTime(nightFraction);
        }
        return daytime;
    }

    public String getSunriseTime() {
        return realTime.getSunriseTime();
    }

    public String getSunsetTime() {
        return realTime.getSunsetTime();
    }
}
