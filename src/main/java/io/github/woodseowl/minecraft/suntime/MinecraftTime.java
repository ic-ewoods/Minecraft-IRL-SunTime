package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

public class MinecraftTime {
    public static final int DAYLENGTH = 24000;
    public static final int SUNRISE = 0;
    public static final int SUNSET = 12000;

    private final Long worldTime;

    public MinecraftTime(World world) {
        worldTime = world.getTime();
    }

    public String getDayTime() {
        return worldTime.toString();
    }

    public int calculateDayTime(double dayFraction) {
        return (int)Math.round(getLengthOfDay() * dayFraction) + SUNRISE;
    }

    public int calculateNightTime(double nightFraction) {
        return (int)Math.round(getLengthOfNight() * nightFraction) + SUNSET;
    }

    private Integer getLengthOfDay() {
        return SUNSET - SUNRISE;
    }

    private long getLengthOfNight() {
        return (DAYLENGTH + SUNRISE) - SUNSET;
    }
}
