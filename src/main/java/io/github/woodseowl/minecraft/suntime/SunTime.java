package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

public class SunTime {

    private final MinecraftTime minecraftTime;
    public final RealTime realTime;

    public SunTime(World world) {
        this(world, new SunCalculator());
    }

    public SunTime(World world, SunCalculator sunCalculator) {
        minecraftTime = new MinecraftTime(world);
        realTime = new RealTime(sunCalculator);
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
