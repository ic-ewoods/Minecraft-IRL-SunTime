package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

import java.util.Calendar;

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

    public String getRealMinecraftTime() {
        return realTime.getMinecraftFormatTime();
    }

    public String getRealDayTime() {
        if (realTime.isDay()) {
            double dayFraction = realTime.getDayFraction();
            Integer daytime = minecraftTime.calculateDayTime(dayFraction);
            return daytime.toString();
        } else {
            double nightFraction = realTime.getNightFraction();
            Integer daytime = minecraftTime.calculateNightTime(nightFraction);
            return daytime.toString();
        }

    }

}
