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

    public int calculateDayTime(long dayFraction) {
        return Math.round(getLengthOfDay() * dayFraction) + SUNRISE;
    }

    public int calculateNightTime(long nightFraction) {
        return Math.round(getLengthOfNight() * nightFraction) + SUNSET;
    }

    private long getLengthOfDay() {
        return SUNSET - SUNRISE;
    }

    private long getLengthOfNight() {
        return (DAYLENGTH + SUNRISE) - SUNSET;
    }
}
